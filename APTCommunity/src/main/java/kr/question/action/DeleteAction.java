package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int que_num = Integer.parseInt(request.getParameter("que_num"));
		QuestionDAO dao = QuestionDAO.getInstance();
		QuestionVO db_question = dao.getQuestion(que_num);
		//로그인한 회원번호와 작성자 회원번호 일치여부체크 관리자여부체크
		if(user_num !=  db_question.getMem_num() && user_auth != 9) {
			//로그인한 회원번호와 작성자 회원번호가 불일치 관리자가아님
			return "/WEB-INF/views/common/notice.jsp";
		}
		//로그인한 회원번호와 작성자 회원번호 일치 관리자일경우
		dao.deleteQuestion(que_num);
		
		//파일 삭제
		FileUtil.removeFile(request, db_question.getFilename());
		
		return "redirect:/question/questionList.do";
	}

}
