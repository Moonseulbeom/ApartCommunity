package kr.booking.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;

public class CancelMyBookingAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		request.setCharacterEncoding("utf-8");
		
		int bk_num = Integer.parseInt(request.getParameter("bk_num"));
		BookingDAO dao = BookingDAO.getInstance();
		dao.deleteBookingFromNum(bk_num);
		
		//현재 계정의 시설 예약 리스트를 가져옴
		List<BookingVO> list = dao.getMyBooking(user_num);
		
		String result = "success";
		if (list == null) {
			result = "null";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("result", result);
		
		return "/WEB-INF/views/member/myPageBooking.jsp";
	}
}
