package kr.booking.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.booking.dao.BookingDAO;
import kr.controller.Action;

public class BookingGetTimeListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 되어있는지 체크, 아닐시 로그인폼으로 이동
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		if (user_num==null) {
			mapAjax.put("result", "logout");
		}
		request.setCharacterEncoding("utf-8");
		
		int room_num = Integer.parseInt(request.getParameter("room_num"));
		String bk_date = request.getParameter("bk_date");
		List<String> list = null;
		
		BookingDAO dao = BookingDAO.getInstance();
		list = dao.getTimeList(room_num, bk_date);
		//System.out.println("액션 확인용 "+list.size());
		if (list.isEmpty()) {
			mapAjax.put("result", "noList");
		}else {
			mapAjax.put("result", "success");
			mapAjax.put("list", list);
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
