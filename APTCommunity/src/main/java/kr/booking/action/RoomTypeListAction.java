package kr.booking.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.booking.vo.Room_infoVO;
import kr.controller.Action;

public class RoomTypeListAction implements Action{

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
		// 3개의 버튼을 모두 비교해서 값을 넣음, 이상한 값일 경우, 예약/신청 메인으로 이동
		String room_Name = request.getParameter("meet");
		while (true) {
			if (room_Name!= null && !"".equals(room_Name))
				break;
			room_Name = request.getParameter("lib");
			if (room_Name!= null && !"".equals(room_Name))
				break;
			room_Name = request.getParameter("house");
			if (room_Name!= null && !"".equals(room_Name))
				break;
			request.setAttribute("notice_msg", "잘못된 경로입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/booking/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}// end of while
		System.out.println("시설타입 값 확인용 : "+room_Name);
		BookingDAO dao = BookingDAO.getInstance();
		List<Room_infoVO> list = dao.getRoomInfoList(room_Name);
		
		//모두 비교해서 세션에 값 저장
		request.setAttribute("room_Name", room_Name);
		request.setAttribute("list", list);
		
		//JSP 경로 반환
		return "/WEB-INF/views/booking/roomTypeList.jsp";
	}
}
