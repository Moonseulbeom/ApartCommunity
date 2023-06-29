package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class ModifyCategoryNoticeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
			
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		MultipartRequest multi = FileUtil.createFile(request);
		
		int category_status = Integer.parseInt(multi.getParameter("category_status"));
		int no_num = Integer.parseInt(multi.getParameter("no_num"));
		String filename = multi.getFilesystemName("filename");
		
		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeVO vo = dao.getNoticeVO(no_num);
		
		NoticeVO notice = new NoticeVO();
		notice.setCategory_status(category_status);
		notice.setDept(4);
		notice.setTitle(multi.getParameter("title"));
		notice.setContent(multi.getParameter("content"));
		notice.setFilename(filename);
		notice.setStatus(Integer.parseInt(multi.getParameter("status")));
		notice.setNo_num(no_num);
		if(filename != null) {
			FileUtil.removeFile(request, vo.getFilename());
		}
		dao.modifyNotice(notice);
		request.setAttribute("no_num", no_num);
		return "/WEB-INF/views/notice/modifyCategoryNotice.jsp";
	}

}
