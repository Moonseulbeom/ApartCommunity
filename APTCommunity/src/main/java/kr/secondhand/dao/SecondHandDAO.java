package kr.secondhand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.secondhand.vo.SecondHandVO;
import kr.secondhand.vo.SecondhandFavVO;
import kr.secondhand.vo.SecondhandReplyVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class SecondHandDAO {
	/*
		division : 판매 및 구매 분류번호
		1 -> 판매
		2 -> 구매
	*/
	
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
		
	//중고구매 글작성 (division : 2 -> 구매)
	public void insertSeBuy(SecondHandVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO secondhand (se_num, mem_num, division, title, content, filename, ip)"
					+ " VALUES (secondhand_seq.nextval,?,2,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_num());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getFilename());
			pstmt.setString(5, vo.getIp());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally{
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//중고판매 글작성 (division : 1 -> 판매)
	public void insertSeSale(SecondHandVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
			
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO secondhand (se_num, mem_num, division, title, content, filename, ip)"
					+ " VALUES (secondhand_seq.nextval,?,1,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMem_num());
			pstmt.setString(2, vo.getTitle());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getFilename());
			pstmt.setString(5, vo.getIp());
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
				else if(keyfield.equals("2")) sub_sql += "WHERE m.dongho LIKE ?";
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
	public SecondHandVO getSecondHandDetail(int se_num) throws Exception {
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
				vo.setDivision(rs.getInt("division"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return vo;
	}
	
	//중고구매 글목록
	public List<SecondHandVO> getListSeBuy(int start, int end, String keyfield, String keyword) throws Exception{
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
				else if(keyfield.equals("2")) sub_sql += "WHERE m.dongho LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE s.content LIKE ?";
			}
			sql = "SELECT * FROM (SELECT A.*, ROWNUM RNUM FROM"
					+ " (SELECT * FROM SECONDHAND S JOIN MEMBER M"
					+ " USING(MEM_NUM) "+sub_sql+" ORDER BY S.SE_NUM DESC)A)"
							+ " WHERE RNUM >= ? AND RNUM <= ? AND division=2";
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
	
	//중고판매 글목록
	public List<SecondHandVO> getListSeSale(int start, int end, String keyfield, String keyword) throws Exception{
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
				else if(keyfield.equals("2")) sub_sql += "WHERE m.dongho LIKE ?";
				else if(keyfield.equals("3")) sub_sql += "WHERE s.content LIKE ?";
			}
			sql = "SELECT * FROM (SELECT A.*, ROWNUM RNUM FROM"
					+ " (SELECT * FROM SECONDHAND S JOIN MEMBER M"
					+ " USING(MEM_NUM) "+sub_sql+" ORDER BY S.SE_NUM DESC)A)"
							+ " WHERE RNUM >= ? AND RNUM <= ? AND division=1";
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
	
	//동,호 불러오기 -> 사이드바에 사용
	public String getDongho(int mem_num) throws Exception {
		Connection conn = null;
	   	PreparedStatement pstmt = null;
	   	ResultSet rs = null;
	   	String sql = null;
	   	String dongho = null;
	    	
	   	try {
	   		conn = DBUtil.getConnection();
	   		sql = "SELECT dongho FROM member WHERE mem_num=?";
	   		pstmt = conn.prepareStatement(sql);
	   		pstmt.setInt(1, mem_num);
	   		rs = pstmt.executeQuery();
	   		if(rs.next()) {
	   			dongho = rs.getString(1);
	   		}
	   	}catch(Exception e) {
	   		throw new Exception(e);
	   	}finally {
	   		DBUtil.executeClose(rs, pstmt, conn);
	   	}
    	return dongho;
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
	
	//중고구매 글 수정
	public void updateSecondhand(SecondHandVO vo) throws Exception {
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
			sql = "UPDATE secondhand SET title=?, content=?, modify_date=SYSDATE"+sub_sql+",ip=?"
					+ " WHERE se_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(++cnt, vo.getTitle());
			pstmt.setString(++cnt, vo.getContent());
			if(vo.getFilename() != null) {
				pstmt.setString(++cnt, vo.getFilename());
			}
			pstmt.setString(++cnt, vo.getIp());
			pstmt.setInt(++cnt, vo.getSe_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}

	//글삭제
	public void deleteSecondHand(int se_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt1 = null; //-> 찜버튼
		PreparedStatement pstmt2 = null; //-> 댓글
		PreparedStatement pstmt3 = null; //-> 글
		String sql = null;
		SecondHandVO vo = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			vo = new SecondHandVO();
			//찜삭제
			sql = "DELETE FROM SECONDHAND_FAV WHERE SE_NUM=?";
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setInt(1, se_num);
			pstmt1.executeUpdate();
			//댓글삭제
			sql = "DELETE FROM SECONDHAND_REPLY WHERE SE_NUM=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, se_num);
			pstmt2.executeUpdate();
			//부모글삭제
			sql = "DELETE FROM SECONDHAND WHERE SE_NUM=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, se_num);
			pstmt3.executeUpdate();
			
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt1, conn);
		}
	}
	
	//중고거래-판매 찜버튼
	public void insertFav(SecondhandFavVO favVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO secondhand_fav (fav_num, mem_num, se_num)"
					+ " VALUES(secondhand_fav_seq.nextval, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, favVO.getMem_num());
			pstmt.setInt(2, favVO.getSe_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//찜 개수
	public int selectFavCount(int se_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM secondhand_fav WHERE se_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, se_num);
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
	
	//찜 삭제
	public void deleteFav(int fav_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM secondhand_fav WHERE fav_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fav_num);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//찜 정보(게시물 번호를 이용한) -> 회원이 게시물을 호출할 때 찜 선택여부 표시
	public SecondhandFavVO selectFav(SecondhandFavVO favVO) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SecondhandFavVO fav = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM secondhand_fav WHERE se_num=? AND mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, favVO.getSe_num());
			pstmt.setInt(2, favVO.getMem_num());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fav = new SecondhandFavVO();
				fav.setFav_num(rs.getInt("fav_num"));
				fav.setSe_num(rs.getInt("se_num"));
				fav.setMem_num(rs.getInt("mem_num"));
				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fav;
	}	
	
	//마이페이지에 사용할 찜 정보 -> 내가 선택한 찜 목록
	public List<SecondHandVO> getListSecondhandFav(int start, int end, int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SecondHandVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum"
					+ " FROM (SELECT * FROM secondhand s JOIN member m"
					+ " USING(mem_num) JOIN secondhand_fav f USING(se_num)"
					+ " WHERE f.mem_num=? ORDER BY fav_num DESC)a)"
					+ " WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<SecondHandVO>();
			
			while(rs.next()) {
				SecondHandVO vo = new SecondHandVO();
				vo.setSe_num(rs.getInt("se_num"));
				vo.setTitle(StringUtil.useNoHtml(rs.getString("title")));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setDongho(rs.getString("dongho"));
				vo.setDivision(rs.getInt("division"));

				list.add(vo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 등록
	public void insertReplySe(SecondhandReplyVO vo) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO secondhand_reply(re_num, content, ip, mem_num, se_num, reg_date)"
					+ " VALUES(secondhand_reply_seq.nextval,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getContent());
			pstmt.setString(2, vo.getIp());
			pstmt.setInt(3, vo.getMem_num());
			pstmt.setInt(4, vo.getSe_num());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplySecondhandCount(int se_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM secondhand_reply s JOIN member m"
					+ " ON s.mem_num=m.mem_num"
					+ " WHERE s.se_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, se_num);
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
	public List<SecondhandReplyVO> getListReplyBoard(int start, int end, int se_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SecondhandReplyVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM"
					+ " (SELECT * FROM secondhand_reply s JOIN member m"
					+ " USING(mem_num) WHERE s.se_num=? ORDER BY s.re_num DESC)a)"
					+ " WHERE rnum >= ? AND rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, se_num);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			list = new ArrayList<SecondhandReplyVO>();
			while(rs.next()) {
				SecondhandReplyVO reply = new SecondhandReplyVO();
				reply.setRe_num(rs.getInt("re_num"));
				if(rs.getDate("modify_date") != null) {
					reply.setModify_date(rs.getDate("modify_date"));
				}
				reply.setContent(StringUtil.useBrNoHtml(rs.getString("content")));
				reply.setSe_num(rs.getInt("se_num"));
				reply.setMem_num(rs.getInt("mem_num"));
				reply.setDongho(rs.getString("dongho"));
				reply.setReg_date(rs.getDate("reg_Date"));
				
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 상세
	public SecondhandReplyVO getReplySecondhand(int re_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SecondhandReplyVO reply = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM secondhand_reply WHERE re_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, re_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new SecondhandReplyVO();
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
	public void updateReplySecondhand(SecondhandReplyVO reply) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE secondhand_reply SET content=?, modify_date=SYSDATE, ip=?"
					+ " WHERE re_num=?";
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
	public void deleteReplySecondhand(int re_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM secondhand_reply WHERE re_num=?";
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
