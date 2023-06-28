package kr.booking.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.Room_infoVO;
import kr.controller.Action;

public class AdminRoomTypeListFormAction implements Action{

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
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if (user_auth!=9) {
			request.setAttribute("notice_msg", "권한이 없어 메인페이지로 이동합니다");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		request.setCharacterEncoding("utf-8");
		
		String room_name = request.getParameter("room_name");
		BookingDAO dao = BookingDAO.getInstance();
		List<Room_infoVO> list = dao.getRoomInfoList(room_name);
		
		request.setAttribute("list", list);
		request.setAttribute("room_name", room_name);
		
		System.out.println("여기까지 잘됨");
		
		//JSP 경로 반환
		return "/WEB-INF/views/booking/adminRoomTypeListForm.jsp";
	}

}
