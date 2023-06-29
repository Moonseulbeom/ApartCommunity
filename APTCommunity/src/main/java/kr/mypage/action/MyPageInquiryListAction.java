package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;

public class MyPageInquiryListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}
		
		InquiryDAO dao = InquiryDAO.getInstance();
		List<InquiryVO> list = dao.myListInquiry(user_num);
		
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/member/myPageInquiryList.jsp";
	}

}
