package kr.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;

public class MyPageListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";			
		}

		/* 내가 쓴 글 목록(자유게시판,중고거래,하자보수,1:1문의) */
		BoardDAO boardDao = BoardDAO.getinstance();
		List<BoardVO> boardList = boardDao.myListBoard(user_num);
		SecondHandDAO secondhandDao = SecondHandDAO.getinstance();
		List<SecondHandVO> secondhandList = secondhandDao.myListSecondhand(user_num);
		InquiryDAO inquiryDao = InquiryDAO.getInstance();
		List<InquiryVO> inquiryList = inquiryDao.myListInquiry(user_num);
		FixDAO fixDao = FixDAO.getInstance();
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("secondhandList", secondhandList);
		request.setAttribute("inquiryList", inquiryList);
		//request.setAttribute("fixList", fixList);
		
		
		return "/WEB-INF/views/member/myPageList.jsp";
	}

}
