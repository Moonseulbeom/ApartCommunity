package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class DeleteUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String dongho = request.getParameter("dongho");
		String email = request.getParameter("email");
		String passwd = request.getParameter("passwd");
		
		//로그인한 아이디
		String user_dongho = (String)session.getAttribute("user_dongho");
		MemberDAO dao = MemberDAO.getInstance();
		MemberVO db_member = dao.checkMember(dongho);
		boolean check = false;
		
		//사용자가 입력한 아이디가 존재하고
		//각 로그인한 아이디,이메일과 입력한 아이디,이메일이 일치하는지 여부 체크
		if(db_member!=null && dongho.equals(user_dongho) && email.equals(db_member.getEmail())) {
			//비밀번호 일치 여부체크
			check = db_member.isCheckedPassword(passwd);
		}
		
		if(check) {//인증 성공
			//회원탈퇴
			dao.deleteMember(user_num);
			System.out.println("탈퇴 성공");
			session.invalidate();
		}
		
		request.setAttribute("check", check);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/deleteUser.jsp";
	}

}
