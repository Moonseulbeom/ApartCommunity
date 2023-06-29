package kr.fix.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixReplyVO;
import kr.util.PageUtil;

public class FixListReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		//전송된 데이터 반환
		String pageNum = request.getParameter("pageNum");
		if (pageNum==null) {
			pageNum = "1";
		}
		int fix_num = Integer.parseInt(request.getParameter("fix_num"));
		
		FixDAO dao = FixDAO.getInstance();
		int count = dao.getFixReplyCount(fix_num);
		
		//ajax 방식으로 목록을 표시하기 때문에 PageUtil은 페이지수를 표시할 목적이아니라
		//목록 데이터의 페이지 처리를 위해 rownum 번호를 구하는 목적으로 사용함
		//currentPage, count, rowCount
		int rowCount = 10;
		PageUtil page = new PageUtil(Integer.parseInt(pageNum),count, rowCount);
		
		List<FixReplyVO> list = null;
		if (count > 0) {
			list = dao.getListFixReply(page.getStartRow(), page.getEndRow(), fix_num);
		}else {
			list = Collections.emptyList();
		}
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		Map<String, Object> mapAjax = new HashMap<String, Object>();
		
		mapAjax.put("count", count);
		mapAjax.put("rowCount", rowCount);
		mapAjax.put("list", list);
		//로그인한 사람이 작성자인지 체크하기 위해서 전송
		mapAjax.put("user_num", user_num);
		mapAjax.put("user_auth", user_auth);
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
