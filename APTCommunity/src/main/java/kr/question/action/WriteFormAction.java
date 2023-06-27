package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크 회원등급 체크
		HttpSession session = request.getSession();
	Integer user_num = (Integer)session.getAttribute("user_num");
	Integer user_auth = (Integer)session.getAttribute("user_auth");
	if(user_num==null && user_auth !=9) {//로그인 되지 않은 경우,관리자가 아닌경우
		return "redirect:/member/loginForm.do";
	}
	//JSP 경로 반환
	return "/WEB-INF/views/question/questionWriteForm.jsp";
	}

}
