package kr.fix.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.PageUtil;

public class FixListAction implements Action{

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
		
		//로그인 됫을경우
		//하자보수 글 페이징 처리 시작
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null) pageNum = "1"; 
		
		String keyfield = request.getParameter("keyfield");
		String keyword = request.getParameter("keyword");
		if (keyword == null && "".equals(keyword)) {
			keyfield = "2";
		}
		
		FixDAO dao = FixDAO.getInstance();
		int count = dao.getFixCount(keyfield, keyword);
		//페이징 처리
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), count, 10, 5,"fixList.do");
		
		List<FixVO> list = null;
		if(count > 0) {
			list = dao.getListFix(page.getStartRow(), page.getEndRow(),	keyfield, keyword);
		}
		
		//상단 고정 게시글 가져오기 시작 | 부서번호:4(기타), 분류번호:4(하자보수)
		NoticeDAO ndao = NoticeDAO.getInstance();
		//			상단 고정 게시글 함수 구조(부서번호, 분류번호, 시작,  끝)
		List<NoticeVO> nlist = ndao.getFixedList(4, 4, 1, 3);
		
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("nlist", nlist);
		request.setAttribute("page", page.getPage());
		
		//JSP 경로 반환
		return "/WEB-INF/views/fix/fixList.jsp";
	}
}
