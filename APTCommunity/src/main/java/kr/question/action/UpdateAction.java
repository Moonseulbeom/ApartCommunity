package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		int que_num = Integer.parseInt(multi.getParameter("que_num"));
		String filename = multi.getFilesystemName("filename");
		
		QuestionDAO dao = QuestionDAO.getInstance();
		//수정 전 데이터 반환
		QuestionVO db_question = dao.getQuestion(que_num);
		//로그인한 회원이 관리자인지 확인
		if(user_num !=  db_question.getMem_num() && user_auth != 9) {
			//로그인한 회원이 관리자가 아니라면
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원이 관리자일 경우
		QuestionVO question = new QuestionVO();
		question.setQue_num(que_num);
		question.setTitle(multi.getParameter("title"));
		question.setContent(multi.getParameter("content"));
		question.setIp(request.getRemoteAddr());
		question.setFilename(filename);
		
		dao.updateQuestion(question);
		
		//새 파일로 교체할 때 원래 파일제거
		if(filename!=null) {
			FileUtil.removeFile(request, 
					    db_question.getFilename());
		}
		
		return "redirect:/question/questionDetail.do?que_num="+que_num;
	}

}
