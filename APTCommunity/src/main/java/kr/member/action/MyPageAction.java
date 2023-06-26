package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;

public class MyPageAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}
		//로그인이 된 경우
		MemberDAO dao = MemberDAO.getInstance();
		//회원정보
		MemberVO member = dao.getMember(user_num);
		
		
		/* 좋아요 목록 */
		SecondHandDAO shDao = SecondHandDAO.getinstance();
		List<SecondHandVO> favList = shDao.getListSecondhandFav(1, 3, user_num);
		/* 내가 쓴 글 목록(자유게시판,중고거래,하자보수,1:1문의) */
		
		
		
		request.setAttribute("member", member);
		request.setAttribute("favList", favList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
