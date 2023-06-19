package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//로그인 여부 체크
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			//로그인이 안된 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인이 된 경우
		MultipartRequest multi = FileUtil.createFile(request);
		int board_num = Integer.parseInt(multi.getParameter("board_num"));
		String filename = multi.getFilesystemName("filename");
				
		//수정전 데이터 반환
		BoardDAO dao = BoardDAO.getinstance();
		BoardVO db_board = dao.getBoard(board_num);
			
		//로그인한 회원번호와 작성자 회원번호 일치여부 체크
		if(user_num != db_board.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호가 불일치한 경우
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치한 경우
		BoardVO vo = new BoardVO();
		vo.setBoard_num(board_num);
		vo.setTitle(multi.getParameter("title"));
		vo.setContent(multi.getParameter("content"));
		vo.setIp(request.getRemoteAddr());
		vo.setFilename(filename);

		//DAO에 있는 updateBoard에 데이터 넘겨주기
		dao.updateBoard(vo);
				
		//새 파일로 교체할 때 원래 파일 제거(트래쉬메모리 방지)
		if(filename != null) {
			//기존 파일이 있을 때는 새 파일 넣고
			FileUtil.removeFile(request, db_board.getFilename());//기존 파일 제거
		}
				
		//JSP 경로반환
		return "redirect:/board/boardDetail.do?board_num="+board_num;
	}
}
