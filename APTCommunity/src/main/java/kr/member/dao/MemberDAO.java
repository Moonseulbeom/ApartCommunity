package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

public class MemberDAO {
	//싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();
	public static MemberDAO getInstance() {
		return instance;
	}
	private MemberDAO() {}
	
	//회원가입
	public void insertMember(MemberVO member) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0; //시퀀스 번호 저장
		try {
			//커넥션 풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);
			
			//입주민 번호(mem_num) 구하기
			sql= "SELECT member_seq.nextval FROM dual";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1); //컬럼 인덱스
			}
			
			//member 테이블에 데이터 저장
			sql = "INSERT INTO member (mem_num,dongho) "
				+ "VALUES (?,?)";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, num);//시퀀스
			pstmt2.setString(2, member.getDongho());
			pstmt2.executeUpdate();
			
			//member_detail 테이블에 데이터 저장
			sql = "INSERT INTO member_detail (mem_num,"
					+ "name,passwd,phone,email,carnum,reg_date"
					+ ") VALUES(?,?,?,?,?,?,SYSDATE)";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, num);
			pstmt3.setString(2, member.getName());
			pstmt3.setString(3, member.getPasswd());
			pstmt3.setString(4, member.getPhone());
			pstmt3.setString(5, member.getEmail());
			pstmt3.setString(6, member.getCarnum());
			pstmt3.executeUpdate();
			
			//SQL문을 실행해서 모두 성공하면 commit
			conn.commit();
			
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 rollback
			conn.rollback();
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}
	}
	//ID 중복 체크 및 로그인 처리
	public MemberVO checkMember(String dongho) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM member m LEFT OUTER JOIN "
					+ "member_detail d ON m.mem_num = d.mem_num "
					+ "WHERE m.dongho=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, dongho);
			//SQL문 실행
			rs = pstmt.executeQuery();

			if (rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setDongho(rs.getString("dongho"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setCarnum(rs.getString("carnum"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//회원상세 정보(MY페이지
	public MemberVO getMember(int mem_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM member m JOIN " + "member_detail d ON " + "m.mem_num=d.mem_num WHERE m.mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setInt(1, mem_num);
			//SQL문 실행
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberVO();
				member.setMem_num(rs.getInt("mem_num"));
				member.setDongho(rs.getString("dongho"));
				member.setAuth(rs.getInt("auth"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setPhone(rs.getString("phone"));
				member.setEmail(rs.getString("email"));
				member.setCarnum(rs.getString("carnum"));
				member.setReg_date(rs.getDate("reg_date"));
				member.setModify_date(rs.getDate("modify_date"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return member;
	}
	//회원정보 수정
	public void updateMember(MemberVO member) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE member_detail SET name=?," + "phone=?,email=?,carnum=?,"
					+ "modify_date=SYSDATE " + "WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getCarnum());
			pstmt.setInt(5, member.getMem_num());
			//SQL문 실행
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//비밀번호 수정
	public void updatePassword(
			   String passwd, int mem_num)
			             throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE member_detail SET passwd=? "
				+ "WHERE mem_num=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setString(1, passwd);//새비밀번호
			pstmt.setInt(2, mem_num);//회원번호
			//SQL문 실행
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//회원탈퇴(회원정보 삭제)
	
	
	//관리자
	//전체글 개수, 검색글 개수
	//목록, 검색 글 목록
	//회원정보 수정
	
}
