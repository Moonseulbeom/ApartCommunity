package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			//로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		SecondHandVO vo = new SecondHandVO();
		vo.setTitle(multi.getParameter("title"));
		vo.setContent(multi.getParameter("content"));
		vo.setIp(request.getRemoteAddr());
		vo.setFilename(multi.getFilesystemName("filename"));
		vo.setMem_num(user_num);//작성자(회원번호)
		vo.setDivision(Integer.parseInt(multi.getParameter("division")));
		
		SecondHandDAO dao = SecondHandDAO.getinstance();
		dao.insertSecondHand(vo);
		
		//JSP 경로반환
		return "/WEB-INF/views/secondhand/secondhandwrite.jsp";
	}
	
}
