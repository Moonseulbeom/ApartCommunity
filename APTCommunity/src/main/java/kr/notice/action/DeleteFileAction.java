package kr.notice.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.notice.dao.NoticeDAO;
import kr.notice.vo.NoticeVO;
import kr.util.FileUtil;

public class DeleteFileAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>(); 
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		int no_num = Integer.parseInt(request.getParameter("no_num"));
		if (user_num==null) {
			map.put("result", "logout");
		}else {
			NoticeDAO dao = NoticeDAO.getInstance();
			NoticeVO notice = dao.getNoticeVO(no_num);
			dao.deleteFile(no_num);
			//파일 삭제
			FileUtil.removeFile(request, 
					    notice.getFilename());
			map.put("result", "success");
		}
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(map);
		
		request.setAttribute("ajaxData", ajaxData);
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
