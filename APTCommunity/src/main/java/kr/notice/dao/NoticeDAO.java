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
    private static NoticeDAO instance;
    
    static {
        NoticeDAO.instance = new NoticeDAO();
    }
    
    public static NoticeDAO getInstance() {
        return NoticeDAO.instance;
    }
    
    private NoticeDAO() {
    }
    
    public void insertNotice(final NoticeVO notice) throws Exception {
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
            DBUtil.executeClose((ResultSet)null, pstmt, conn);
        }
        DBUtil.executeClose((ResultSet)null, pstmt, conn);
    }
    
    public int getCount(final int dept, final String keyword) throws Exception {
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
        DBUtil.executeClose(rs, pstmt, conn);
        return count;
    }
    
    public List<NoticeVO> getList(final int dept, final String keyword, final int start, final int end) throws Exception {
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
                final NoticeVO notice = new NoticeVO();
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
    
    public NoticeVO getNoticeVO(final int no_num) throws Exception {
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
                noticeVO.setFilename(rs.getString("filename"));
                noticeVO.setReg_date(rs.getDate("reg_date"));
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
}