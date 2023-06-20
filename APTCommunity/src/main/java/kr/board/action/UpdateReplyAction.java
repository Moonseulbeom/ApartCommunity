package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardReplyVO;
import kr.controller.Action;

public class UpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		//전송된 데이터 반환
		//댓글번호
		int re_num = Integer.parseInt(request.getParameter("re_num"));
		//db에 저장된 로그인 정보
		BoardDAO dao = BoardDAO.getinstance();
		BoardReplyVO db_reply = dao.getReplyBoard(re_num);
		//작성자 회원번호로 로그인 되어있는지 확인
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		//조건체크
		if(user_num == null) {
			//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else if(user_num!=null && user_num==db_reply.getMem_num()){
			//로그인이 되어있고
			//로그인한 회원번호와 작성자 회원번호가 일치
			BoardReplyVO reply = new BoardReplyVO();
			reply.setRe_num(re_num);
			reply.setContent(request.getParameter("re_content"));
			reply.setIp(request.getRemoteAddr());
			
			dao.updateReplyBoard(reply);
			
			mapAjax.put("result", "success");
		}else {
			//로그인 되어있고
			//로그인한 회원번호와 작성자 회원번호가 불일치
			mapAjax.put("result", "wrongAccess");
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
