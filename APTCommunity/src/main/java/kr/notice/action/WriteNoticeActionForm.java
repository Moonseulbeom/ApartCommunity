package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class WriteNoticeActionForm implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
        Integer user_auth = (Integer)session.getAttribute("user_auth");
        if (user_auth != 9) {
            return "/WEB-INF/views/common/notice.jsp";
        }
		return "/WEB-INF/views/notice/writeNoticeForm.jsp";
	}

}
