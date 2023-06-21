package kr.fix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.fix.vo.FixReplyVO;
import kr.fix.vo.FixVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class FixDAO {
	//싱글턴 패턴
	private static FixDAO instance = new FixDAO();
	public static FixDAO getInstance() {
		return instance;
	}
	private FixDAO() {}
	
	//총 하자보수 레코드 검색
	public int getFixCount(String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = ""; //기본값을 비워놔야됨
		int count = 0;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE f.title LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM fix f "
				+ "JOIN member m USING(mem_num) " + sub_sql;
			
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(1, "%" + keyword + "%");
			}
			//SQL 실행
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); //컬럼인덱스를 넣어줌
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//하자보수 글 목록 불러오기
	public List<FixVO> getListFix(int start, int end, String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FixVO> list = null;
		String sql = null;
		String sub_sql = ""; //기본값을 비워놔야됨
		int cnt = 0;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				sub_sql += "WHERE f.title LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT mem_num, fix_num, mem_auth, title, content, reg_date, modify_date, filename, ip, dongho, rnum "
				+ "FROM (SELECT a.*, "
				+ "rownum rnum FROM (SELECT * "
				+ "FROM fix f JOIN member m "
				+ "USING(mem_num) " + sub_sql + " ORDER BY "
				+ "f.fix_num DESC)a) "
				+ "WHERE rnum>=? AND rnum<=? ";
			
			System.out.println("하자보수 글목록 확인용 " + sql);
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<FixVO>();
			while(rs.next()) {
				FixVO fix = new FixVO();
				fix.setFix_num(rs.getInt("fix_num"));
				fix.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				fix.setMem_num(rs.getInt("mem_num"));
				fix.setReg_date(rs.getDate("reg_date"));
				fix.setDongHo(rs.getString("dongho"));
				//자바빈을 ArrayList에 저장
				list.add(fix);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//하자보수 글 상세 불러오기
	public FixVO getFix(int fix_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FixVO fix = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM fix f "
					+ "JOIN member m USING(mem_num) "
					+ "LEFT OUTER JOIN member_detail d "
					+ "USING(mem_num) WHERE f.fix_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, fix_num);
			//SQL문을 실행해서 결과행을 ResultSet에 담음
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fix = new FixVO();
				fix.setFix_num(rs.getInt("fix_num"));
				fix.setTitle(rs.getString("title"));
				fix.setContent(rs.getString("content"));
				fix.setReg_date(rs.getDate("reg_date"));
				fix.setModify_date(rs.getDate("modify_date"));
				fix.setDongHo(rs.getString("dongho"));
				fix.setFilename(rs.getString("filename"));
				fix.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return fix;
	}
	
	//하자보수 글쓰기
	public void insertFix(FixVO fix) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO FIX (fix_num, mem_num, mem_auth, title, content, filename, ip) "
				+ "VALUES (fix_seq.nextval, ?, ?, ?, ?, ?, ?)";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, fix.getMem_num());
			pstmt.setInt(2, fix.getMem_auth());
			pstmt.setString(3, fix.getTitle());
			pstmt.setString(4, fix.getContent());
			pstmt.setString(5, fix.getFilename());
			pstmt.setString(6, fix.getIp());

			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//하자보수 글삭제
	public void deleteFix(int fix_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			//댓글 삭제
			
			//부모글 삭제
			sql = "DELETE FROM fix WHERE fix_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, fix_num);
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//하자보수 글수정
	public void updateFix(FixVO fix) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = ""; //대입연산자나 누적으로 쓸수도 있음
		int cnt = 0; //?에 번호를 사용하기 위해 변수지정
		
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(fix.getFilename()!=null) {
				//파일을 업로드한 경우
				sub_sql += ",filename=?";
			}
			
			sql = "UPDATE fix SET title=?,"
				+ "content=?,modify_date=SYSDATE" 
				+  sub_sql + ",ip=? WHERE fix_num=?";
			
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(++cnt, fix.getTitle());
			pstmt.setString(++cnt, fix.getContent());
			if(fix.getFilename()!=null) {
				pstmt.setString(++cnt, fix.getFilename());
			}
			pstmt.setString(++cnt, fix.getIp());
			pstmt.setInt(++cnt, fix.getFix_num());
			
			//SQL문 실행
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//파일삭제(사진파일)
	public void deleteFile(int fix_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션 풀로부터 커넥션을 할당
			 conn = DBUtil.getConnection();
			 //SQL문 작성
			 sql  = "UPDATE fix SET filename='' WHERE fix_num = ? ";
			 //PreparedStatement 객체 생성
			 pstmt = conn.prepareStatement(sql);
			 //?에 데이터 바인딩
			 pstmt.setInt(1, fix_num);
			 //SQL문 실행
			 pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	//하자보수 내가 쓴 글목록
	//하자보수 글목록(관리자)
	/*
	 * ==================================================================
	 * */
	
	//댓글 등록
	public void insertFixReply(FixReplyVO fixReply) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "INSERT INTO fix_reply "
					+ "(re_num,content,ip, "
					+ "mem_num,fix_num) VALUES "
					+ "(fix_reply_seq.nextval,?,?,?,?) ";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, fixReply.getContent());
			pstmt.setString(2, fixReply.getIp());
			pstmt.setInt(3, fixReply.getMem_num());
			pstmt.setInt(4, fixReply.getFix_num());

			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 상세(댓글 수정시 작성자 회원번호 체크 용도로 사용)
	public FixReplyVO getFixReply(int re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FixReplyVO reply = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			//SQL문 작성
			sql = "SELECT * from fix_reply WHERE re_num=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				reply = new FixReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setMem_num(rs.getInt("mem_num"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reply;
	}
	
	//댓글 삭제
	public void deleteFixReply(int re_num)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "DELETE FROM fix_reply WHERE re_num=? ";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, re_num);
			//SQL문 실행
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getFixReplyCount(int fix_num) throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int count = 0;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT COUNT(*) FROM fix_reply f "
					+ "JOIN member m ON f.mem_num=m.mem_num "
					+ "WHERE f.fix_num=? ";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, fix_num);
				//SQL문 실행
				rs = pstmt.executeQuery();
				if (rs.next()) {
					count = rs.getInt(1);
				}
						
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return count;
		}
	
	//댓글 목록
	public List<FixReplyVO>getListFixReply(int start, int end, int fix_num)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<FixReplyVO> list = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "SELECT * FROM (SELECT a.*,"
					+ "rownum rnum FROM (SELECT * "
					+ "FROM fix_reply f JOIN "
					+ "member m USING(mem_num) "
					+ "WHERE f.fix_num=? ORDER BY "
					+ "f.re_num DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";
				
				//pstmt 객체 생성
				pstmt = conn.prepareStatement(sql);
				
				//?에 데이터 바인딩
				pstmt.setInt(1, fix_num);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<FixReplyVO>();
				while(rs.next()) {
					FixReplyVO reply = new FixReplyVO();
					reply.setRe_num(rs.getInt("re_num"));
					//날짜 -> 1분전, 1시간전, 1일전 형식의 문자열로 변환
					reply.setReg_date(DurationFromNow.getTimeDiffLabel(rs.getString("reg_date")));
					if (rs.getString("modify_date")!=null) {
						reply.setModify_date(DurationFromNow.getTimeDiffLabel(rs.getString("modify_date")));
					}
					reply.setContent(StringUtil.useBrNoHtml(rs.getString("content")));
					reply.setFix_num(rs.getInt("fix_num"));
					reply.setMem_num(rs.getInt("mem_num"));
					reply.setDongho(rs.getString("dongho"));
					
					list.add(reply);
				}
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
		}
	
	//댓글 수정
	public void updateFixReply(FixReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE fix_reply SET content=?, modify_date=SYSDATE, ip=? "
				+ "WHERE re_num=? ";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, reply.getContent());
			pstmt.setString(2, reply.getIp());
			pstmt.setInt(3, reply.getRe_num());
			//SQL문 실행
			pstmt.executeUpdate();
					
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	

	
	
	
	
}
