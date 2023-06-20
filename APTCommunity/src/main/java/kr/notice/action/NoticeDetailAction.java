package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.StringUtil;

public class NoticeDetailAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		int no_num = Integer.parseInt(request.getParameter("no_num"));
		if(user_num == null) {
			return "redirect:/member/loginForm.do";
		}
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO notice = dao.getNoticeVO(no_num);
		
		notice.setTitle(StringUtil.useBrNoHtml(notice.getTitle()));
		notice.setContent(StringUtil.useBrNoHtml(notice.getContent()));
		
		request.setAttribute("notice", notice);
		request.setAttribute("user_num", user_num);
		request.setAttribute("user_auth", user_auth);
		
		return "/WEB-INF/views/notice/noticeDetail.jsp";
	}

}
