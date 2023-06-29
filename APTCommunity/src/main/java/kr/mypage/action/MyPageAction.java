package kr.mypage.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.mypage.dao.MyPageDAO;
import kr.mypage.vo.MyPageVO;
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
		/* 내가 쓴 글 목록 */
		MyPageDAO myDao = MyPageDAO.getinstance();
		List<MyPageVO> myList = myDao.myListMyPage(user_num,1,3);
		/* 문의 목록 */
		InquiryDAO inDao = InquiryDAO.getInstance();
		List<InquiryVO> inList = inDao.myListInquiry(user_num,1,3);
		/* 예약 목록 */
		BookingDAO bkDao = BookingDAO.getInstance();
		List<BookingVO> bkList = bkDao.myBookingList(user_num, 1, 3);
		
		request.setAttribute("member", member);
		request.setAttribute("favList", favList);
		request.setAttribute("myList", myList);
		request.setAttribute("inList", inList);
		request.setAttribute("bkList", bkList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/member/myPage.jsp";
	}

}
