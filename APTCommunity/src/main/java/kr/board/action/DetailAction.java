package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		BoardDAO dao = BoardDAO.getinstance();
		
		//레코드 반환받기
		BoardVO vo = dao.getBoard(board_num);
		
		//HTML 태그를 허용하지 않음
		vo.setTitle(StringUtil.useNoHtml(vo.getTitle()));
		//HTML 태그를 허용하지 않으면서 줄바꿈처리
		vo.setContent(StringUtil.useBrNoHtml(vo.getContent()));
		
		request.setAttribute("vo", vo);
		
		//JSP 경로반환
		return "/WEB-INF/views/board/boardDetail.jsp";
	}

}
