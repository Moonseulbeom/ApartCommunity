package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = 
			(Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//자바빈(VO)을 생성하고 전송된 데이터를 자바빈에
		//저장
		MemberVO member = new MemberVO();
		member.setMem_num(user_num);//회원번호
		member.setName(
			  request.getParameter("name"));
		member.setPhone(
			  request.getParameter("phone"));
		member.setEmail(
			  request.getParameter("email"));
		member.setCarnum(
			request.getParameter("carnum"));
		if(request.getParameter("passwd") != null) {//관리자 페이지 수정
			System.out.println(request.getParameter("passwd"));
			member.setPasswd(request.getParameter("passwd"));
			MemberDAO dao = MemberDAO.getInstance();
			dao.updateMember(member);	
			//JSP 경로 반환
			return"redirect:/manager/manageMain.do";
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);		
		
		return "/WEB-INF/views/member/modifyUser.jsp";
	}

}




