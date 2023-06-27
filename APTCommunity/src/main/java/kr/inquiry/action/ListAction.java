package kr.inquiry.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.PageUtil;
import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;


public class ListAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 되어있는지 체크, 아닐시 로그인폼으로 이동
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//게시글 목록
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		InquiryDAO dao = InquiryDAO.getInstance();
		int count = dao.getInquiryCount(keyfield, keyword);
		//keyfield,keyword,currentPage,count,
		//rowCount,pageCount,요청URL
		PageUtil page = new PageUtil(keyfield,keyword,Integer.parseInt(pageNum),
						count,20,10,"list.do");
		
		List<InquiryVO> list = null;
		if(count > 0) {
			list = dao.getListInquiry(page.getStartRow(),page.getEndRow(),
					keyfield,keyword);
		}
		
		//상단 고정 게시글 가져오기 시작 | 부서번호:4(기타), 분류번호:4(하자보수)
				NoticeDAO ndao = NoticeDAO.getInstance();
				//			상단 고정 게시글 함수 구조(부서번호, 분류번호, 시작,  끝)
				List<NoticeVO> fixedList = ndao.getFixedList(4, 6, 1, 3);
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());	
		request.setAttribute("fixedList", fixedList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/inquiry/list.jsp";
	}

}