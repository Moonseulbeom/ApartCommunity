package kr.secondhand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondhandReplyVO;

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {
			//로그인 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {
			//로그인 된 경우
			//전송
			request.setCharacterEncoding("UTF-8");
			
			SecondhandReplyVO reply = new SecondhandReplyVO();
			reply.setContent(request.getParameter("re_content"));
			reply.setIp(request.getRemoteAddr());
			reply.setMem_num(user_num);//회원번호(댓글 작성자)
			reply.setSe_num(Integer.parseInt(request.getParameter("se_num")));
			
			SecondHandDAO dao = SecondHandDAO.getinstance();
			dao.insertReplySe(reply);
			
			mapAjax.put("result", "success");
		}
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		//JSP에 반영
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP경로반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
