package kr.question.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.question.dao.QuestionDAO;
import kr.question.vo.QuestionVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//로그인 여부 체크, 관리자체크		
		HttpSession session = request.getSession();
		
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num==null && user_auth !=9) {//로그인 되지 않은 경우,관리자가 아닌경우
			return "redirect:/member/loginForm.do";
		}
		
	//로그인 된 경우, 관리자확인
		MultipartRequest multi = FileUtil.createFile(request);
		//자바빈(VO) 생성
		QuestionVO question = new QuestionVO();
		question.setTitle(multi.getParameter("title"));//제목
		question.setContent(multi.getParameter("content"));//내용
		question.setIp(request.getRemoteAddr());//IP
		question.setFilename(multi.getFilesystemName("filename"));//파일명
		question.setMem_num(user_num);//작성자(회원번호) 관리자만 작성가능한데?
		
		QuestionDAO dao = QuestionDAO.getInstance();
		dao.insertQuestion(question);
		//JSP경로 반환
		return "/WEB-INF/views/question/questionWrite.jsp";
	}

}























