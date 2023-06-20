package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;

public class DeleteNoticeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		Integer user_num = (Integer)session.getAttribute("user_num");
		int no_num = Integer.parseInt(request.getParameter("no_num"));
		
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		if(user_auth != 9) {
			request.setAttribute("notice_msg", "권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO vo = dao.getNoticeVO(no_num);
		int dept = vo.getDept();
		dao.deleteNotice(no_num);
		
		request.setAttribute("dept", dept);
		return "/WEB-INF/views/notice/deleteNotice.jsp";
	}

}
