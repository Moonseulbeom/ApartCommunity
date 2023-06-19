package kr.question.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.question.vo.QuestionVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class QuestionDAO {
	//싱글턴 패턴
		private static QuestionDAO instance = new QuestionDAO();
		public static QuestionDAO getInstance() {
			return instance;
		}
		private QuestionDAO() {}
		
		//글 등록
		public void insertQuestion(QuestionVO question)
										throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = null;
			
			try {
				//커넥션풀로부터 커넥션을 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "INSERT INTO question (in_num,"
						+ "title,content,filename,ip,mem_num) "
						+ "VALUES (question_seq.nextval,?,?,?,?,?)";
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터를 바인딩
				pstmt.setString(1, question.getTitle());
				pstmt.setString(2, question.getContent());
				pstmt.setString(3, question.getFilename());
				pstmt.setString(4, question.getIp());
				pstmt.setInt(5, question.getMem_num());
				//SQL문 실행
				pstmt.executeUpdate();
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(null, pstmt, conn);
			}
		}
		//검색 레코드 수
		public int getQuestionCount(String keyfield,
	            					String keyword)
	            				throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			String sub_sql = "";
			int count = 0;

			try {
				//커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();

				if(keyword != null && !"".equals(keyword)) {
					if(keyfield.equals("1")) sub_sql += "WHERE q.title LIKE ?";
					else if(keyfield.equals("2")) sub_sql += "WHERE q.content LIKE ?";
				}

				//SQL문 작성
				sql = "SELECT COUNT(*) FROM question q "
						+ "JOIN member m USING(mem_num) " + sub_sql;
				//PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				if(keyword != null 
						&& !"".equals(keyword)) {
					pstmt.setString(1, "%" + keyword + "%");
				}

				//SQL 실행
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
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
		public List<QuestionVO> getListQuestion(
			      int start, int end,
		 String keyfield,String keyword)
	                throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<QuestionVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			
			if(keyword != null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql += "WHERE q.title LIKE ?";
				else if(keyfield.equals("2")) sub_sql += "WHERE q.content LIKE ?";
			}
			
			//SQL문 작성
			sql = "SELECT * FROM (SELECT a.*,"
				+ "rownum rnum FROM (SELECT * "
				+ "FROM question q JOIN member m "
				+ "USING(mem_num) " + sub_sql + " ORDER BY "
				+ "q.que_num DESC)a) "
				+ "WHERE rnum>=? AND rnum<=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			if(keyword != null 
					    && !"".equals(keyword)) {
				pstmt.setString(
						++cnt, "%" + keyword + "%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//SQL문 실행
			rs = pstmt.executeQuery();
			list = new ArrayList<QuestionVO>();
			while(rs.next()) {
				QuestionVO question = new QuestionVO();
				question.setQue_num(
						rs.getInt("Que_num"));
				question.setTitle(
						StringUtil.useNoHtml(
							rs.getString("title")));
				question.setReg_date(
						rs.getDate("reg_date"));
				
				//자바빈을 ArrayList에 저장
				list.add(question);
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
		
		//파일 삭제
		
		//글 수정
		
		
		//댓글 등록
		
		//댓글 갯수
		
		//댓글 목록
		
		//댓글 상세
		
		//댓글 수정
		
		//댓글 삭제
	}

