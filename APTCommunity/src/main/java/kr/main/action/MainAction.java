package kr.main.action; //대문 페이지

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//공지사항 게시판 신규 데이터 처리
		NoticeDAO noticeDao = NoticeDAO.getInstance();
		List<NoticeVO> noticelist = 
				noticeDao.getList(
						         1, null, 1, 5);
				
		//자유게시판 신규 데이터 처리
		BoardDAO boardDao = BoardDAO.getinstance();
		List<BoardVO> boardList = 
				boardDao.getListBoard(
						         1, 5, null, null);
		
		request.setAttribute("noticelist", noticelist);
		request.setAttribute("boardList", boardList);
		
		//JSP 경로 반환
		return "/WEB-INF/views/main/main.jsp";
	}

}
