package kr.notice.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class WriteNoticeAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		MultipartRequest multi = FileUtil.createFile(request);
		int check = Integer.parseInt(multi.getParameter("status"));
		int dept = Integer.parseInt(multi.getParameter("keyfield_dept"));
		if(check != 1) {
			check = 0;
		}
		NoticeVO notice = new NoticeVO();
		notice.setDept(dept);
		notice.setTitle(multi.getParameter("title"));
		notice.setContent(multi.getParameter("content"));
		notice.setFilename(multi.getFilesystemName("filename"));
		notice.setIp(request.getRemoteAddr());
		notice.setCategory_status(Integer.parseInt(multi.getParameter("category_status")));
		notice.setStatus(Integer.parseInt(multi.getParameter("status")));
		
		NoticeDAO dao = NoticeDAO.getInstance();
		dao.insertNotice(notice);
		
		request.setAttribute("dept", dept);
		return "/WEB-INF/views/notice/writeNotice.jsp";
	}

}
