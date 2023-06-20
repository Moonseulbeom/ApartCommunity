package kr.secondhand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.secondhand.vo.SecondHandVO;
import kr.util.DBUtil;

public class SecondHandDAO {
	//싱글턴 패턴
		private static SecondHandDAO instance = new SecondHandDAO();
		public static SecondHandDAO getinstance() {
			return instance;
		}
		private SecondHandDAO() {}
		//싱글턴 패턴끝
		
	/*
	 	1.모두 로그인 후 이용가능
	 	2.글작성/수정/목록
	 	3.모두 댓글사용할예정
	 */
	//중고거래 글작성 -> mem_num, division : 임의의 값으로 지정한 상태
	public void insertSecondHand(SecondHandVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO secondhand (se_num, mem_num, division, title, content, filename, ip)"
					+ " VALUES (secondhand_seq.nextval,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_num());
			pstmt.setInt(2, vo.getDivision());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getFilename());
			pstmt.setString(6, vo.getIp());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//총 레코드 수(검색레코드 수)
	public int getSecondHandCount(String keyfield, String keyword) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			//커넥션 풀로부터 커넥션을 할당받음
			conn = DBUtil.getConnection();
	        //조건체크
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE s.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE s.content LIKE ?";
			}
			//SQL문 작성
			sql = "SELECT COUNT(*) FROM secondhand s "
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
	
	//중고거래 글상세
	public SecondHandVO getSecondHand(int se_num) throws Exception {
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SecondHandVO vo = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM SECONDHAND S JOIN MEMBER M USING(MEM_NUM)"
					+ " LEFT OUTER JOIN MEMBER_DETAIL D USING(MEM_NUM)"
					+ " WHERE S.SE_NUM=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, se_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new SecondHandVO();
				vo.setSe_num(rs.getInt("se_num"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setDongho(rs.getString("dongho"));
				vo.setFilename(rs.getString("filename"));
				vo.setMem_num(rs.getInt("mem_num"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setModify_date(rs.getDate("modify_date"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return vo;
	}
	
	//중고거래 글목록
	public List<SecondHandVO> getListSecondHand(int start, int end, String keyfield, String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SecondHandVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE s.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE m.id LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE s.content LIKE ?";
			}
			sql = "SELECT * FROM (SELECT A.*, ROWNUM RNUM FROM"
					+ " (SELECT * FROM SECONDHAND S JOIN MEMBER M"
					+ " USING(MEM_NUM) "+sub_sql+" ORDER BY S.SE_NUM DESC)A)"
							+ " WHERE RNUM >= ? AND RNUM <= ?";
			pstmt = conn.prepareStatement(sql);
			if(keyword != null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<SecondHandVO>();
			while(rs.next()) {
				SecondHandVO vo = new SecondHandVO();
				vo.setSe_num(rs.getInt("se_num"));
				vo.setTitle(rs.getString("title"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setDongho(rs.getString("dongho"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//파일삭제
	public void deleteFile(int se_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE secondhand SET filename='' WHERE se_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, se_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//중고거래 글 수정
	public void updateSecondHand(SecondHandVO vo) throws Exception {
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
			sql = "UPDATE secondhane SET title=?, content=?, modify_date=SYSDATE"+sub_sql+",ip=?"
					+ " WHERE se_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, vo.getTitle());
			pstmt.setString(++cnt, vo.getContent());
			if(vo.getFilename() != null) {
				pstmt.setString(++cnt, vo.getFilename());
			}
			pstmt.setString(++cnt, vo.getIp());
			pstmt.setInt(++cnt, vo.getSe_num());
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글삭제
	public void deleteSecondHand(int se_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt1 = null; //-> 댓글
		PreparedStatement pstmt2 = null; //-> 글
		//PreparedStatement pstmt3 = null; -> 찜버튼
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			//댓글삭제
			sql = "DELETE FROM SECONDHAND_REPLY WHERE SE_NUM=?";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, se_num);
			pstmt1.executeUpdate();
			//부모글삭제
			sql = "DELETE FROM SECONDHAND WHERE SE_NUM=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, se_num);
			pstmt2.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			//DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	//중고거래-판매 찜버튼
	
	//댓글 등록
	
	//댓글 개수
	
	//댓글 목록
	
	//댓글 상세
	
	//댓글 수정
	
	//댓글 삭제
}