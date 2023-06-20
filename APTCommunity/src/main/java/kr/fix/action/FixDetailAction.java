package kr.fix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.util.StringUtil;

public class FixDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//글번호
		int fix_num= Integer.parseInt(request.getParameter("fix_num"));
		
		FixDAO dao = FixDAO.getInstance();
		FixVO fix = dao.getFix(fix_num);
		
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		//작성자가 아니거나, 관리자가 아닐경우에 리스트목록으로
		if (user_num != fix.getMem_num() && user_auth != 9) {
			request.setAttribute("notice_msg", "조회 권한이 없습니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/fix/fixList.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//HTML 태그를 허용하지 않음
		fix.setTitle(StringUtil.useNoHtml(fix.getTitle()));
		//HTML 태그를 허용하지 않으면서 줄바꿈 처리
		fix.setContent(StringUtil.useBrNoHtml(fix.getContent()));
		
		request.setAttribute("fix", fix);
		
		return "/WEB-INF/views/fix/fixDetail.jsp";
	}
}
