package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		// 전송된 데이터 반환
        // dong과 ho값을 연결
        String dong = request.getParameter("dong");
        String ho = request.getParameter("ho");
        String dongho = dong + "-" + ho;
		String passwd = request.getParameter("passwd");

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(dongho);
		boolean check = false;
		
		if(member!=null) {
			//비밀번호 일치 여부 체크
			check = member.isCheckedPassword(passwd);
			//로그인 실패시 auth 체크용
			request.setAttribute("auth", member.getAuth());
		}
		
		if(check) {//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user_num", member.getMem_num());
			session.setAttribute("user_dongho", member.getDongho());
			session.setAttribute("user_auth", member.getAuth());
			
			//인증 성공시 호출
			return "redirect:/main/main.do";
		}
		//인증 실패시 호출
		return "/WEB-INF/views/member/login.jsp";
	}

}
