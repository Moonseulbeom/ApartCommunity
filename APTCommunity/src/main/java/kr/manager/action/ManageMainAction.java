package kr.manager.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ManageMainAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null || user_auth < 9) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		return "/WEB-INF/views/manager/manageMain.jsp";
	}

}
