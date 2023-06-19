package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;

public class NoticeListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null)pageNum = "1";
		
		String keyword = request.getParameter("keyword");
		NoticeDAO dao = NoticeDAO.getInstance();
		
		
		return "/WEB-INF/views/notice/NoticeList.jsp";
	}

}
