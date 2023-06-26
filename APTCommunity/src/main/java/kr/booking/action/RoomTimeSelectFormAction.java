package kr.booking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.Room_infoVO;
import kr.controller.Action;

public class RoomTimeSelectFormAction implements Action{

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
		int room_num = Integer.parseInt(request.getParameter("room_num"));
		BookingDAO dao = BookingDAO.getInstance();
		Room_infoVO room = dao.getOneRoomInfo(room_num);
		
		request.setAttribute("room", room);
		
		return "/WEB-INF/views/booking/roomTimeSelectForm.jsp";
	}
}
