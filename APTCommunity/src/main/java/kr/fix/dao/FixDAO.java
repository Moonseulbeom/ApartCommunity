package kr.fix.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.fix.vo.FixVO;
import kr.util.DBUtil;
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
				if(keyfield.equals("1")) sub_sql += "WHERE f.title LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT mem_num, fix_num, mem_auth, title, content, reg_date, modify_date, filename, ip, dongho, rnum "
				+ "FROM (SELECT a.*, "
				+ "rownum rnum FROM (SELECT * "
				+ "FROM fix f RIGHT JOIN member m "
				+ "USING(mem_num) " + sub_sql + " ORDER BY "
				+ "f.fix_num DESC)a) "
				+ "WHERE rnum>=? AND rnum<=? AND fix_num is not null";
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
			sql = "SELECT * FROM zboard b "
					+ "JOIN zmember m USING(mem_num) "
					+ "LEFT OUTER JOIN zmember_detail d "
					+ "USING(mem_num) WHERE b.board_num=?";
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
	
	

	//하자보수 내가 쓴 글목록
	//하자보수 글목록(관리자)
}
