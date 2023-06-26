package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.StringUtil;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num == null) {//로그인 되지 않은 경우
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "redirect:/member/loginForm.do";			
		}
		
		int in_num = Integer.parseInt(request.getParameter("in_num"));
		
		InquiryDAO dao = InquiryDAO.getInstance();
		InquiryVO inquiry = dao.getInquiry(in_num);
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != inquiry.getMem_num() && user_auth != 9) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//큰 따옴표 처리
		//(수정폼의 input태그에서 오동작)
		inquiry.setTitle(StringUtil.parseQuot(inquiry.getTitle()));		
		
		//로그인이 되어 있고 로그인한 회원번호와
		//작성자 회원번호 일치
		request.setAttribute("inquiry", inquiry);
		
		return "/WEB-INF/views/inquiry/updateForm.jsp";
	}

}



