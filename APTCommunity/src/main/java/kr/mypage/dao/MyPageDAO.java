package kr.mypage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.mypage.vo.MyPageVO;
import kr.util.DBUtil;

public class MyPageDAO {
	//싱글톤 패턴
		private static MyPageDAO instance = new MyPageDAO();
		public static MyPageDAO getinstance() {
			return instance;
		}
		private MyPageDAO() {}
		//싱글톤 패턴 끝
	
	//내가 쓴 글
	public List<MyPageVO> myListMyPage(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyPageVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
		/*	sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM"
				+ " (SELECT '일대일문의' category, in_num, title, reg_date, cnt"
				+ " FROM inquiry LEFT OUTER JOIN (SELECT in_num, COUNT(*) cnt"
				+ " FROM inquiry_manage GROUP BY in_num) USING(in_num)"
				+ " UNION ALL"
				+ " SELECT '중고거래' category, se_num, title, reg_date, cnt"
				+ " FROM secondhand LEFT OUTER JOIN (SELECT se_num, COUNT(*) cnt"
				+ " FROM secondhand_reply GROUP BY se_num) USING(se_num)"
				+ " UNION ALL"
				+ " SELECT '하자보수' category, fix_num, title, reg_date, cnt"
				+ " FROM fix LEFT OUTER JOIN (SELECT fix_num, COUNT(*) cnt"
				+ " FROM fix_reply GROUP BY fix_num) USING(fix_num)"
				+ " UNION ALL"
				+ " SELECT '자유게시판' category, board_num, title, reg_date, cnt"
				+ " FROM board LEFT OUTER JOIN (SELECT board_num, COUNT(*) cnt"
				+ " FROM board_reply GROUP BY board_num) USING(board_num)"
				+ " ORDER BY reg_date DESC)a) WHERE rnum >= ? AND rnum <= ?";
			*/
			sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM "
			+ "(SELECT '일대일문의' category, in_num, mem_num, title, reg_date, cnt FROM inquiry LEFT OUTER JOIN "
			+ "(SELECT in_num, COUNT(*) cnt FROM inquiry_manage GROUP BY in_num) USING(in_num) "
			+ "UNION ALL SELECT '중고거래' category, se_num, mem_num, title, reg_date, cnt FROM secondhand LEFT OUTER JOIN "
			+ "(SELECT se_num, COUNT(*) cnt FROM secondhand_reply GROUP BY se_num) USING(se_num) UNION ALL "
			+ "SELECT '하자보수' category, fix_num, mem_num, title, reg_date, cnt FROM fix LEFT OUTER JOIN "
			+ "(SELECT fix_num, COUNT(*) cnt FROM fix_reply GROUP BY fix_num) USING(fix_num) UNION ALL "
			+ "SELECT '자유게시판' category, board_num, mem_num, title, reg_date, cnt FROM board LEFT OUTER JOIN "
			+ "(SELECT board_num, COUNT(*) cnt FROM board_reply GROUP BY board_num) USING(board_num) "
			+ "ORDER BY reg_date DESC)a) WHERE rnum >= ? AND rnum <= ? and mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 100);
			pstmt.setInt(3, mem_num);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MyPageVO>();
			while(rs.next()) {
				MyPageVO vo = new MyPageVO();
				vo.setCategory(rs.getString("category"));
				vo.setNum(rs.getInt("in_num"));
				vo.setMem_num(mem_num);
				vo.setTitle(rs.getString("title"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setCnt(rs.getInt("cnt"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//내가 쓴 댓글
	
	public List<MyPageVO> MyPageReplyList(int mem_num) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyPageVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
	
			sql = "SELECT * FROM(SELECT a.*, rownum rnum FROM "
			+ "(SELECT '일대일문의' category, re_num, mem_num, content, reg_date FROM inquiry_manage UNION ALL "
			+ "SELECT '중고거래' category, re_num, mem_num, content, reg_dat, FROM secondhand_reply UNION ALL "
			+ "SELECT '하자보수' category, re_num, mem_num, content, reg_dateFROM fix_reply UNION ALL "
			+ "SELECT '자유게시판' category, RE_num, mem_num, content, reg_dateFROM board_reply UNION ALL "
			+ "ORDER BY reg_date DESC)a) WHERE rnum >= ? AND rnum <= ? and mem_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);
			pstmt.setInt(2, 20);
			pstmt.setInt(3, mem_num);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<MyPageVO>();
			while(rs.next()) {
				MyPageVO vo = new MyPageVO();
				vo.setCategory(rs.getString("category"));
				vo.setNum(rs.getInt("in_num"));
				vo.setMem_num(mem_num);
				vo.setTitle(rs.getString("title"));
				vo.setReg_date(rs.getDate("reg_date"));
				vo.setCnt(rs.getInt("cnt"));
				
				list.add(vo);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
}
/*
 SELECT * FROM
(SELECT a.*, rownum rnum FROM
(SELECT '일대일문의' cate, in_num, mem_num, title, reg_date, cnt FROM inquiry LEFT OUTER JOIN (SELECT in_num, COUNT(*) cnt FROM inquiry_manage GROUP BY in_num) USING(in_num)
UNION ALL
SELECT '중고거래' cate, se_num, mem_num, title, reg_date, cnt FROM secondhand LEFT OUTER JOIN (SELECT se_num, COUNT(*) cnt FROM secondhand_reply GROUP BY se_num) USING(se_num)
UNION ALL
SELECT '하자보수' cate, fix_num, mem_num, title, reg_date, cnt FROM fix LEFT OUTER JOIN (SELECT fix_num, COUNT(*) cnt FROM fix_reply GROUP BY fix_num) USING(fix_num)
UNION ALL
SELECT '자유게시판' cate, board_num, mem_num, title, reg_date, cnt FROM board LEFT OUTER JOIN (SELECT board_num, COUNT(*) cnt FROM board_reply GROUP BY board_num) USING(board_num)
 ORDER BY reg_date DESC)a)
WHERE rnum >= 1 AND rnum <= 10;
 
 */