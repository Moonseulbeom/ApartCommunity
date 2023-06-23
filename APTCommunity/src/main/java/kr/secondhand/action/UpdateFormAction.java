package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			//로그인되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		int se_num = Integer.parseInt(request.getParameter("se_num"));
		
		SecondHandDAO dao = SecondHandDAO.getinstance();
		SecondHandVO vo = dao.getSecondHandDetail(se_num);
		
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != vo.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			//작업중단
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//큰 따옴표 처리(수정폼의 input태그에서 오동작)
		vo.setTitle(StringUtil.parseQuot(vo.getTitle()));
		
		//로그인이 되어있고 로그인한 회원번호와 작성자 회원번호가 일치할 경우
		request.setAttribute("vo", vo);
		
		if(vo.getDivision() == 1) {
			return "/WEB-INF/views/secondhand/seSaleUpdateForm.jsp";
		}
		
		//JSP 경로반환
		return "/WEB-INF/views/secondhand/seBuyUpdateForm.jsp";
	}

}
