package kr.manager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PageUtil;

public class ManageServiceListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		Map<String,List<MemberVO>> mapAjax = new HashMap<String,List<MemberVO>>();
		
		if(user_num == null || user_auth < 9) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
		String keyfield = request.getParameter("mem_select");
		String keyword = request.getParameter("keyword");
		
		MemberDAO dao = MemberDAO.getInstance();
		if(keyfield != null) {//search한 경우
			request.setCharacterEncoding("utf-8");
			
			int count = dao.getMemberCountByAdmin(keyfield, keyword);
			PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, count, "manageMain.do");
			
			List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
			mapAjax.put("list", list);
			request.setAttribute("keyword", keyword);
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			
			request.setAttribute("ajaxData", ajaxData);
			//JSP 경로 반환
			return "/WEB-INF/views/common/ajax_view.jsp";
		}
		
		int count = dao.getMemberCountByAdmin(keyfield, keyword);
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, count, "manageMain.do");
		
		List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		
		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		return "/WEB-INF/views/manager/manage-serviceList.jsp";
	}

}
