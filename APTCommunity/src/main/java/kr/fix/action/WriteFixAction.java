package kr.fix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.util.FileUtil;

public class WriteFixAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = FileUtil.createFile(request);
		FixVO fix = new FixVO();
		fix.setMem_num(user_num);
		fix.setMem_auth(user_auth);
		fix.setTitle(multi.getParameter("title"));
		fix.setContent(multi.getParameter("content"));
		fix.setFilename(multi.getFilesystemName("filename"));
		fix.setIp(request.getRemoteAddr());
		
		FixDAO dao = FixDAO.getInstance();
		dao.insertFix(fix);
		
		return "/WEB-INF/views/fix/writeFix.jsp";
	}

}
