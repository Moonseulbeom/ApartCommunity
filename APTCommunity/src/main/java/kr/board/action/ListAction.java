package kr.board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.PageUtil;
import kr.util.StringUtil;

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
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		
		BoardDAO dao = BoardDAO.getinstance();
		int count = dao.getBoardCount(keyfield, keyword);
		
						//keyfield,keyword,currentPage(현재페이지),count,rowCount,pageCount,요청URL
		PageUtil page = new PageUtil(keyfield,keyword,Integer.parseInt(pageNum),count,20,10,"list.do");
		
		List<BoardVO> list = null;
		if(count > 0) {
			list = dao.getListBoard(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		}
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		//상단고정 게시글 가져오기(notice)
		NoticeDAO ndao = NoticeDAO.getInstance();
		/* 
			getFixedList(int dept, int category_status, int start, int end)
			dept : 분류번호(4:기타)
			category_status : 분류번호(2:자유게시판,3:중고거래)
		 */
		List<NoticeVO> fixedList = ndao.getFixedList(4, 2, 1, 3);
		for(NoticeVO no : fixedList) {
			no.setTitle(StringUtil.useNoHtml(no.getTitle()));
		}
		request.setAttribute("fixedList", fixedList);		
		
		//JSP 경로 반환
		return "/WEB-INF/views/board/boardList.jsp";
	}

}
