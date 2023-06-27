package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int que_num = Integer.parseInt(request.getParameter("que_num"));
		QuestionDAO dao = QuestionDAO.getInstance();
		QuestionVO question = dao.getQuestion(que_num);
		
		//HTML 태그를 허용하지 않음
		question.setTitle(StringUtil.useNoHtml(question.getTitle()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		question.setContent(StringUtil.useBrNoHtml(question.getContent()));
		
		request.setAttribute("question", question);
		
		return "/WEB-INF/views/question/questionDetail.jsp";
	}

}
