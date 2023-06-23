package kr.manager.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PageUtil;

public class ManageMainAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null || user_auth < 9) {
			request.setAttribute("notice_msㅈg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
		String keyfield = request.getParameter("mem_select");
		String keyword = request.getParameter("keyword");
		
		System.out.println(keyfield);
		
		MemberDAO dao = MemberDAO.getInstance();
		int count = dao.getMemberCountByAdmin(keyfield, keyword);
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, count, "manageMain.do?");
		
		List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		return "/WEB-INF/views/manager/manageMain.jsp";
	}

}
