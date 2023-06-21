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

public class FixWriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String,String>();
	      
	      HttpSession session = request.getSession();
	      Integer user_num = (Integer)session.getAttribute("user_num");
	      
	      if(user_num==null) {//로그인 되지 않은 경우
	         mapAjax.put("result", "logout");
	      }else {//로그인 된 경우
	         //전송된 데이터 인코딩 처리
	         request.setCharacterEncoding("utf-8");
	         
	         FixReplyVO reply = new FixReplyVO();
	         reply.setMem_num(user_num);//회원번호(댓글 작성자)
	         reply.setContent(request.getParameter("content"));
	         reply.setIp(request.getRemoteAddr());
	         reply.setFix_num(Integer.parseInt(request.getParameter("fix_num")));
	         
	         FixDAO dao = FixDAO.getInstance();
	         dao.insertFixReply(reply);
	         
	         mapAjax.put("result", "success");
	      }
	      
	      //JSON 데이터 생성
	      ObjectMapper mapper = new ObjectMapper();
	      String ajaxData = mapper.writeValueAsString(mapAjax);
	      
	      request.setAttribute("ajaxData", ajaxData);
	      //JSP 경로 반환
	      return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
