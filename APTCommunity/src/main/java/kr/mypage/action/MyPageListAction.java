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
import kr.util.PageUtil;

public class MyPageListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}
	
		//게시글 목록
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		MemberDAO mDao = MemberDAO.getInstance();
		MemberVO member = mDao.getMember(user_num);
		int count = mDao.getMemberCountByAdmin(keyfield, keyword);
		
		PageUtil page = new PageUtil(keyfield,keyword,Integer.parseInt(pageNum),count,15,10,"myPageList.do");
		
		MyPageDAO dao = MyPageDAO.getinstance();
		List<MyPageVO> list = dao.myListMyPage(user_num,1,5);
		
		request.setAttribute("list", list);
		request.setAttribute("member", member);
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());

		return "/WEB-INF/views/member/myPageList.jsp";
	}

}
