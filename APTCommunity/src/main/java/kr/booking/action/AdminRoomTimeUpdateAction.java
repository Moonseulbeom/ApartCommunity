package kr.booking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;

public class AdminRoomTimeUpdateAction implements Action{

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
		
		//status 활성화=1, 비활성화=0
		//활성화일 경우, 해당날짜 예약 정보만 삭제하고, 비활성화일 경우, 해당날짜 시간대 모두 예약
		int status = Integer.parseInt(request.getParameter("status"));
		BookingDAO dao = BookingDAO.getInstance();
		int room_num = Integer.parseInt(request.getParameter("room_num"));
		String bk_date = request.getParameter("bk_date");
		
		String r_name = request.getParameter("room_name")+" - "+request.getParameter("room_type");
		if (status==0) {//비활성화
			//먼저 해당 날짜에 예약된 모든 정보를 삭제한다.
			dao.deleteBookingFromDate(room_num, bk_date);
			//자바빈에 데이터 적재
			BookingVO booking = new BookingVO();
			booking.setMem_num(user_num);
			booking.setRoom_num(room_num);
			booking.setBook_mem(1);
			booking.setBk_status(1);
			booking.setBk_date(bk_date);
			booking.setStart_time(request.getParameter("start_time"));
			booking.setEnd_time(request.getParameter("end_time"));
			dao.insertMemberBooking(booking);
			
			request.setAttribute("notice_msg", r_name + " 의 비활성화를 완료하였습니다.");
		}else {//활성화
			int cnt = dao.getTimeCount(room_num, bk_date);
			if (cnt!=0) {//하나라도 존재하면
				request.setAttribute("notice_msg", r_name + " 의 해당날짜는 이미 활성화된 날짜입니다.");
			}else {
				dao.deleteBookingFromDate(room_num, bk_date);
				request.setAttribute("notice_msg", r_name + " 의 활성화를 완료하였습니다.");
			}
		}
		
		request.setAttribute("notice_url", request.getContextPath()+"/booking/roomTimeSelectForm.do?room_num="+String.valueOf(room_num));
		
		//JSP반환
		return "/WEB-INF/views/common/alert_singleView.jsp";
	}
}
