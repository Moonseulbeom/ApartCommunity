package kr.question.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			int que_num = Integer.parseInt(request.getParameter("que_num"));
			QuestionDAO dao = QuestionDAO.getInstance();
			QuestionVO db_question = dao.getQuestion(que_num);
			//로그인한 회원번호와 작성자 회원번호 관리자 여부 체크
			if(user_num !=  db_question.getMem_num() && user_auth != 9) {
				//로그인한 회원이 관리자가 아니라면
				mapAjax.put("result", "wrongAccess");				
			}else {
				//파일 정보삭제
				dao.deleteFile(que_num);
				//파일 삭제
				FileUtil.removeFile(request, db_question.getFilename());
				mapAjax.put("result", "success");
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
