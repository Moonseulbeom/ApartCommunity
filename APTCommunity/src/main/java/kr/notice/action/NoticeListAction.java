package kr.notice.action;

import kr.notice.vo.NoticeVO;
import java.util.List;
import javax.servlet.http.HttpSession;
import kr.util.PageUtil2;
import kr.util.StringUtil;
import kr.notice.dao.NoticeDAO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import kr.controller.Action;

public class NoticeListAction implements Action
{
    public String execute( HttpServletRequest request,  HttpServletResponse response) throws Exception {
    	
        HttpSession session = request.getSession();
        Integer user_num = (Integer)session.getAttribute("user_num");
        
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
        int dept = Integer.parseInt(request.getParameter("dept"));
        
        request.setCharacterEncoding("utf-8");
        NoticeDAO dao = NoticeDAO.getInstance();
        //상단 게시글 목록
        List<NoticeVO> fixedList = dao.getFixedList(dept, 1, 5);
        for(NoticeVO no : fixedList) {
        	no.setTitle(StringUtil.useNoHtml(no.getTitle()));
        }
        //게시글 목록
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
         String keyword = request.getParameter("keyword");
         int count = dao.getCount(dept, keyword);
         
        PageUtil2 page = new PageUtil2(Integer.toString(dept), keyword, Integer.parseInt(pageNum), count, 10, 10, "noticeList.do?dept=" + dept);
        List<NoticeVO> list = null;
        if (count > 0) {
            list = dao.getList(dept, keyword, page.getStartRow(), page.getEndRow());
            for(NoticeVO no : list) {
            	no.setTitle(StringUtil.useNoHtml(no.getTitle()));
            }
        }
        request.setAttribute("fixedList", fixedList);
        request.setAttribute("dept", dept);
        request.setAttribute("count", count);
        request.setAttribute("list", list);
        request.setAttribute("page", page.getPage());
        return "/WEB-INF/views/notice/NoticeList.jsp";
    }
}