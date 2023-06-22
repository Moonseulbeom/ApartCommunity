package kr.notice.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;

public class GetMemberNameAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		NoticeDAO dao = NoticeDAO.getInstance();
		String [] name = dao.getDongho(user_num).split("-");
		String dong = name[0];
		String ho = name[1];
		Map<String, String>map = new HashMap<String, String>();
		map.put("dong", dong);
		map.put("ho", ho);
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		
		request.setAttribute("ajaxData", ajaxData);
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
