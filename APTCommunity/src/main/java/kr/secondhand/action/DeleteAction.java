package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.FileUtil;

public class DeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		if(user_num == null) {
			//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		int se_num = Integer.parseInt(request.getParameter("se_num"));
		SecondHandDAO dao = SecondHandDAO.getinstance();
		SecondHandVO db_secondhand = dao.getSecondHandDetail(se_num);
		
		//로그인한 회원번호와 작성자 회원번호가 일치하는지 체크
		if(user_num != db_secondhand.getMem_num() && user_auth != 9) {
			//로그인한 회원번호와 작성자 회원번호가 불일치
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		dao.deleteSecondHand(se_num);
		//파일 삭제
		FileUtil.removeFile(request, db_secondhand.getFilename());
		
		System.out.println("구분 : " + db_secondhand.getDivision());
		
		if(db_secondhand.getDivision()==1) {
			return "redirect:/secondhand/seSaleList.do";
		}
		
		return "redirect:/secondhand/seBuyList.do";
		
	}

}
