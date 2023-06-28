package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {//로그인 되지 않은 경우
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "redirect:/member/loginForm.do";			
		}
		
		int que_num = Integer.parseInt(request.getParameter("que_num"));
		
		QuestionDAO dao = QuestionDAO.getInstance();
		QuestionVO question = dao.getQuestion(que_num);
		//로그인한 유저가 관리자인지 체크
		if(user_num != question.getMem_num() && user_auth !=9) {
			//로그인한 유저가 관리자가 아님
			return "/WEB-INF/views/common/notice.jsp";
		}
		//큰 따옴표 처리
		//(수정폼의 input태그에서 오동작)
		question.setTitle(StringUtil.parseQuot(question.getTitle()));
		
		//로그인한 유저가 관리자일경우
		request.setAttribute("question", question);
		
		return "/WEB-INF/views/question/questionUpdateForm.jsp";
	}

}
