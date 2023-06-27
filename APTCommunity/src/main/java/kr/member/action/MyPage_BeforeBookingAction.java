package kr.member.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;
import kr.util.PageUtil;

public class MyPage_BeforeBookingAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		HttpSession session = request.getSession(); 
		Integer mem_num = (Integer)session.getAttribute("user_num"); 
		String bk_date = request.getParameter("bk_date");
		BookingDAO dao = BookingDAO.getInstance();
		int count = dao.getBeforeMyBookingListCount(mem_num, bk_date);
		
		//AJAX 방식으로 목록을 표시하기 때문에 PageUtil은 페이지 수를 표시할 목적이 아니라
		//목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 목적으로 사용함
		int rowCount = 5;
		PageUtil page = new PageUtil(Integer.parseInt(pageNum), count, rowCount);
		
		List<BookingVO> list = null;
		String isEmp = null;
		if (count > 0) {
			list = dao.getBeforeMyBookingList(page.getStartRow(), page.getEndRow(), bk_date, mem_num);
			isEmp = "yes";
		}else {
			list = Collections.emptyList();
			isEmp = "no";
		}
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("isEmp", isEmp);
		mapAjax.put("list", list);
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
