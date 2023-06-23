package kr.booking.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		//방선택에서 이름, 타입 가져와서 넘겨주고, 시간선택 Form에서 ajax 처리를 진행 하면 된다.
		String room_Name = request.getParameter("room_Name");
		String room_Type = request.getParameter("room_Type");
		
		request.setAttribute("room_Name", room_Name);
		request.setAttribute("room_Type", room_Type);
		
		return "/WEB-INF/views/booking/roomTimeSelectForm.jsp";
	}
}
