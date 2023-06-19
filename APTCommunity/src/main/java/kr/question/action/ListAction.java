package kr.question.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.PageUtil;

public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum == null) pageNum = "1";
		
		String keyfield = 
				request.getParameter("keyfield");
		String keyword = 
				request.getParameter("keyword");
		
		QuestionDAO dao = QuestionDAO.getInstance();
		int count = dao.getQuestionCount(keyfield, keyword);
				
		PageUtil page = 
				new PageUtil(keyfield,keyword,
						Integer.parseInt(pageNum),
						count,20,10,"list.do");
		
		List<QuestionVO> list = null;
		if(count > 0) {
			list = dao.getListQuestion(
					page.getStartRow(),
					page.getEndRow(),
					keyfield,keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());		
		
		//JSP 경로 반환
		return "/WEB-INF/views/question/list.jsp";
	}

}