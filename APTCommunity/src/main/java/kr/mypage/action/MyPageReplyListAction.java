package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.mypage.dao.MyPageDAO;
import kr.mypage.vo.MyPageVO;

public class MyPageReplyListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}
		
		MemberDAO mdao = MemberDAO.getInstance();
		MemberVO member = mdao.getMember(user_num);
		if(member.getMem_num() != user_num) {
			return "";
		}
		
		MyPageDAO dao = MyPageDAO.getinstance();
		List<MyPageVO> list = dao.MyPageReplyList(user_num,1,100);
		
		request.setAttribute("list", list);
		request.setAttribute("member", member);
		
		System.out.println(member.getMem_num());
		
		return "/WEB-INF/views/member/myPageReplyList.jsp";
	}

}
