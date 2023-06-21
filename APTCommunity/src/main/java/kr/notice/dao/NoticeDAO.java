package kr.notice.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import kr.util.DBUtil;
import kr.notice.vo.NoticeVO;

public class NoticeDAO
{
    private static NoticeDAO instance = new NoticeDAO();
    
    public static NoticeDAO getInstance() {
        return NoticeDAO.instance;
   }
    
    private NoticeDAO() { }
    //게시글 작성
    public void insertNotice(NoticeVO notice) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = null;
        try {
            conn = DBUtil.getConnection();
            sql = "INSERT INTO notice (no_num, dept, title, content, filename, ip, category_status, status) VALUES (notice_seq.nextval,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, notice.getDept());
            pstmt.setString(2, notice.getTitle());
            pstmt.setString(3, notice.getContent());
            pstmt.setString(4, notice.getFilename());
            pstmt.setString(5, notice.getIp());
            pstmt.setInt(6, notice.getCategory_status());
            pstmt.setInt(7, notice.getStatus());
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            DBUtil.executeClose(null, pstmt, conn);
        }
    }
    //게시물 갯수 
    public int getCount(int dept, String keyword) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String sub_sql = "";
        int count = 0;
        try {
            if (keyword != null && "".equals(keyword)) {
                sub_sql = " AND ( title LIKE ? OR content LIKE ? )";
            }
            conn = DBUtil.getConnection();
            sql = "SELECT COUNT(*) FROM notice WHERE dept = ?" + sub_sql;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dept);
            if (keyword != null && "".equals(keyword)) {
                pstmt.setString(2, "%" + keyword + "%");
                pstmt.setString(3, "%" + keyword + "%");
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        return count;
    }
    //상단 고정 게시글
    public List<NoticeVO> getFixedList( int dept, int category_status, int start, int end) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sql = null;
    	List<NoticeVO> list = null;
    	
    	try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM ( SELECT a.*, rownum rnum FROM ( SELECT * FROM notice WHERE dept = ? AND category_status = ? AND status = 1"
					+ " ORDER BY no_num DESC )a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept);
			pstmt.setInt(2, category_status);
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
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
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
    	return list;
    }
    //게시물 리스트 
    public List<NoticeVO> getList( int dept,  String keyword,  int start,  int end) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        String sub_sql = "";
        int cnt = 0;
        List<NoticeVO> list = null;
        try {
            conn = DBUtil.getConnection();
            if (keyword != null && !"".equals(keyword)) {
                sub_sql = " AND ( title LIKE ? OR content LIKE ? )";
            }
            sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM notice WHERE dept = ?" + sub_sql + " ORDER BY no_num DESC)a) WHERE rnum >= ? AND rnum <= ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(++cnt, dept);
            if (keyword != null && !"".equals(keyword)) {
                pstmt.setString(++cnt, "%" + keyword + "%");
                pstmt.setString(++cnt, "%" + keyword + "%");
            } 
            pstmt.setInt(++cnt, start);
            pstmt.setInt(++cnt, end);
            rs = pstmt.executeQuery();
            list = new ArrayList<NoticeVO>();
            while (rs.next()) {
                NoticeVO notice = new NoticeVO();
                notice.setNo_num(rs.getInt("no_num"));
                notice.setTitle(rs.getString("title"));
                notice.setReg_date(rs.getDate("reg_date"));
                list.add(notice);
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        DBUtil.executeClose(rs, pstmt, conn);
        return list;
    }
   //상세 페이지
    public NoticeVO getNoticeVO( int no_num) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;
        NoticeVO noticeVO = null;
        try {
            conn = DBUtil.getConnection();
            sql = "SELECT * FROM notice WHERE no_num = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no_num);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                noticeVO = new NoticeVO();
                noticeVO.setNo_num(rs.getInt("no_num"));
                noticeVO.setDept(rs.getInt("dept"));
                noticeVO.setTitle(rs.getString("title"));
                noticeVO.setContent(rs.getString("content"));
                noticeVO.setStatus(rs.getInt("status"));
                noticeVO.setFilename(rs.getString("filename"));
                noticeVO.setReg_date(rs.getDate("reg_date"));
                noticeVO.setCategory_status(rs.getInt("category_status"));
            }
        }
        catch (Exception e) {
            throw new Exception(e);
        }
        finally {
            DBUtil.executeClose(rs, pstmt, conn);
        }
        DBUtil.executeClose(rs, pstmt, conn);
        return noticeVO;
    }
    //게시글 수정
    public void modifyNotice(NoticeVO notice) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql = null;
    	
    	try {
    		conn = DBUtil.getConnection();
    		sql = "UPDATE notice SET dept = ?, title = ?, content = ?, status = ?, filename = ?, modify_date = SYSDATE WHERE no_num = ?";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, notice.getDept());
    		pstmt.setString(2, notice.getTitle());
    		pstmt.setString(3, notice.getContent());
    		pstmt.setInt(4, notice.getStatus());
    		pstmt.setString(5, notice.getFilename());
    		pstmt.setInt(6, notice.getNo_num());
    		
    		pstmt.executeUpdate();
    		
		} catch (Exception e) {
			throw new Exception(e);		
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}				
    }
    //파일 삭제
    public void deleteFile(int no_num) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql = null;
    	
    	try {
    		conn = DBUtil.getConnection();
    		sql = "UPDATE notice SET filename = '' WHERE no_num = ? ";
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setInt(1, no_num);
    		
    		pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
    }
    //게시글 삭제
    public void deleteNotice(int no_num)throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql = null;
    	
    	try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM notice WHERE no_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no_num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
    }
}