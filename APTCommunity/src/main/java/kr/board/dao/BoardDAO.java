package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.board.vo.BoardVO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class BoardDAO {
	//싱글톤 패턴
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getinstance() {
		return instance;
	}
	private BoardDAO() {}
	//싱글톤 패턴 끝
	
	//글 등록
	public void insertBoard(BoardVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO board (board_num, mem_num, title, content, filename, ip,reg_date)"
					+ " VALUES (board_seq.nextval,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_num());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getFilename());
			pstmt.setString(5, vo.getIp());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 레코드 수(검색레코드 수)
	public int getBoardCount(String keyfield,String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
	      
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
	         
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
			}
	         
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM board b "
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
	//글 목록(검색글 목록)
	public List<BoardVO> getListBoard(int start, int end, 
	                        String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
	      
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
	         
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE b.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.dongho LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE b.content LIKE ?";
			}
	         
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM"
	         		+ " (SELECT * FROM board b JOIN member m"
	         		+ " USING(mem_num) " + sub_sql + " ORDER BY b.board_num DESC)a)"
	         				+ " WHERE rnum>=? AND rnum<=?";
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
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBoard_num(rs.getInt("board_num"));
				vo.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setDongho(rs.getString("dongho"));
	            
	            //자바빈을 ArrayList에 저장
				list.add(vo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
			return list;
	}
	
	//글 상세
	public BoardVO getBoard(int board_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO vo = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM board b JOIN member m USING(mem_num)"
					+ " LEFT OUTER JOIN member_detail d USING(mem_num)"
					+ " WHERE b.board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new BoardVO();
				vo.setBoard_num(rs.getInt("board_num"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDongho(rs.getString("dongho"));
				vo.setFilename(rs.getString("filename"));
				vo.setMem_num(rs.getInt("mem_num"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setModify_date(rs.getDate("modify_date"));
			}
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return vo;
	}
	
	//파일삭제
	public void deleteFile(int board_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE board SET filename='' WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 수정
	public void updateBoard(BoardVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;//파일삭제 관련
		
		try {
			conn = DBUtil.getConnection();
			if(vo.getFilename() != null) {
				sub_sql += ",filename=?";
			}
			sql = "UPDATE board SET title=?, content=?, modify_date=SYSDATE"+sub_sql+",ip=?"
					+ " WHERE board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, vo.getTitle());
			pstmt.setString(++cnt, vo.getContent());
			if(vo.getFilename() != null) {
				pstmt.setString(++cnt, vo.getFilename());
			}
			pstmt.setString(++cnt, vo.getIp());
			pstmt.setInt(++cnt, vo.getBoard_num());
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글 삭제
	public void deleteBoard(int board_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//댓글삭제
			sql = "DELETE FROM board_reply WHERE board_num=?";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, board_num);
			pstmt1.executeUpdate();
			//부모글삭제
			sql = "DELETE FROM board WHERE board_num=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, board_num);
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	//댓글 -> AJAX 통신
	//댓글 등록
	public void insertReplyBoard(BoardReplyVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO board_reply(re_num, content, ip, mem_num, board_num)"
					+ " VALUES(board_reply_seq.nextval, ?,?,1,?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getIp());
			pstmt.setInt(3, vo.getBoard_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplyBoardCount(int board_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM board_reply b JOIN member m"
					+ " ON b.mem_num=m.mem_num"
					+ " WHERE b.board_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
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
	public List<BoardReplyVO> getListReplyBoard(int start,int end,int board_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReplyVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM"
					+ " (SELECT * FROM baord_reply r JOIN member m"
					+ " USING(mem_num)"
					+ " WHERE r.board_num=? ORDER BY r.re_num DESC)a)"
					+ " WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardReplyVO>();
			while(rs.next()) {
				BoardReplyVO reply = new BoardReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				if(rs.getDate("modify_date") != null) {
					//reply.setModify_date(DurationFromNow.getTimeDiffLabel(rs.getDate("modify_date")));
				}
				reply.setContent(StringUtil.useBrNoHtml(rs.getString("content")));
				reply.setBoard_num(rs.getInt("board_num"));
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
	
	//댓글 상세(댓글 수정,삭제시 작성자 회원번호 체크 용도)
	public BoardReplyVO getReplyBoard(int re_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardReplyVO reply = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM board_reply WHERE re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new BoardReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				reply.setMem_num(rs.getInt("mem_num"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reply;
	}
	
	//댓글 수정
	public void updateReplyBoard(BoardReplyVO reply) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE board_reply SET content=?, modify_date=SYSDATE, ip=?"
					+ "WHERE re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getContent());
			pstmt.setString(2, reply.getIp());
			pstmt.setInt(3, reply.getRe_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 삭제
	public void deleteReplyBoard(int re_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM board_reply WHERE re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}
