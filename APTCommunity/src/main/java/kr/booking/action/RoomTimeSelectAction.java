package kr.booking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;

public class RoomTimeSelectAction implements Action{

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
		request.setCharacterEncoding("utf-8");
		//VO 모델에 데이터 적재
		BookingVO booking = new BookingVO();
		booking.setMem_num(user_num);
		booking.setRoom_num(Integer.parseInt(request.getParameter("room_num")));
		booking.setBook_mem(Integer.parseInt(request.getParameter("book_mem")));
		booking.setBk_status(1);
		booking.setBk_date(request.getParameter("bk_date"));
		booking.setStart_time(request.getParameter("start_time"));
		booking.setEnd_time(request.getParameter("end_time"));
		
		//DB에 데이터 삽입하기
		BookingDAO dao = BookingDAO.getInstance();
		dao.insertMemberBooking(booking);
		
		request.setAttribute("notice_msg", "시설 예약이 완료되었습니다.");
		request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
		
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}
}
