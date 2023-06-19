package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class RegisterUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈을 생성하고 전송된 데이터를 자바빈에 담음
		MemberVO member = new MemberVO();
		//dong과 ho값을 연결
		String dongho = request.getParameter("dong") + "-" + request.getParameter("ho");
		member.setDongho(dongho);
		member.setName(request.getParameter("name"));
		member.setPasswd(
				request.getParameter("passwd"));
		member.setPhone(
		            request.getParameter("phone"));
		member.setEmail(
				    request.getParameter("email"));
		member.setCarnum(request.getParameter("carnum"));
		
		//MemberDAO 호출
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);		
		//JSP 경로 반환
		return "/WEB-INF/views/member/registerUser.jsp";
	}

}
