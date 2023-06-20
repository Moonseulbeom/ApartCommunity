package kr.fix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.util.StringUtil;

public class FixUpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 되어있는지 체크, 아닐시 로그인폼으로 이동
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		int fix_num = Integer.parseInt(request.getParameter("fix_num"));
			
		FixDAO dao = FixDAO.getInstance();
		FixVO fix = dao.getFix(fix_num);
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != fix.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//큰 따옴표 처리
		//(수정폼의 input태그에서 오동작)
		fix.setTitle(StringUtil.parseQuot(fix.getTitle()));
		
		//로그인이 되어 있고 로그인한 회원번호와
		//작성자 회원번호 일치
		request.setAttribute("fix", fix);
		return null;
	}
}
