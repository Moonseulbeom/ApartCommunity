package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;

public class MyPage_BookingAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}
		
		//현재 계정의 시설 예약 리스트를 가져옴
		BookingDAO dao = BookingDAO.getInstance();
		List<BookingVO> list = dao.getMyBooking(user_num);
		
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/member/myPageBooking.jsp";
	}
}
