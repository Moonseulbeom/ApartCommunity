package kr.fix.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixReplyVO;

public class FixUpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//댓글번호
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		
		FixDAO dao = FixDAO.getInstance();
		FixReplyVO db_reply = dao.getFixReply(re_num);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		if (user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num==db_reply.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 일치
			//자바빈을 생성하고 전송된 데이터를 저장
			FixReplyVO reply = new FixReplyVO();
			reply.setRe_num(re_num);
			reply.setContent(request.getParameter("content"));
			reply.setIp(request.getRemoteAddr());
			dao.updateFixReply(reply);
			mapAjax.put("result", "success");
		}else {
			//로그인한 회원번호와 작성자 회원번호 "불"일치
			mapAjax.put("result", "wrongAccess");
		}
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
