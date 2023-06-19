package kr.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.notice.vo.NoticeVO;
import kr.util.DBUtil;

public class NoticeDAO {
	//싱글턴 패턴
	private static NoticeDAO instance = new NoticeDAO();
	public static NoticeDAO getInstance() {
		return instance;
	}
	private NoticeDAO() {}
	//글작성
	public void insertNotice(NoticeVO notice) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO notice (no_num, dept, title, content, filename, ip, category_status, status)"
					+ " VALUES (notice_seq.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, notice.getDept());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setString(4, notice.getFilename());
			pstmt.setString(5, notice.getIp());
			pstmt.setInt(6, notice.getCategory_status());
			pstmt.setInt(7, notice.getStatus());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//총 레코드 수
	public int getCount(int dept,String keyword) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		
		try {
			if(keyword != null && "".equals(keyword)) {
				sub_sql = " AND ( title LIKE ? OR content LIKE ? )";
			}
			conn = DBUtil.getConnection();
			sql = "SELECT COUNT(*) FROM notice WHERE dept = ?"+sub_sql;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept);
			if(keyword != null && "".equals(keyword)) {
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, "%"+keyword+"%");
			}
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return  count;
	}
	//글 목록
	public List<NoticeVO> getList(int dept, String keyword, int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = null;
		int cnt = 0;
		List<NoticeVO> list = null;
		
		try {
			conn = DBUtil.getConnection();
			if(keyword != null && "".equals(keyword)) {
				sub_sql = " AND ( title LIKE ? OR content LIKE ? )";
			}
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM notice WHERE dept = ?" +sub_sql
					+ " ORDER BY no_num DESC)a) WHERE rnum>=? AND rnum<=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, dept);
			if(keyword != null && "".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeVO>();
			while(rs.next()) {
				NoticeVO notice = new NoticeVO();
				notice.setNo_num(rs.getInt("no_num"));
				notice.setTitle(rs.getString("title"));
				notice.setReg_date(rs.getDate("reg_date"));
				
				list.add(notice);
			}

		} catch (Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	//글 상세
	//파일 삭제
	//글 수정
	//글 삭제
}
