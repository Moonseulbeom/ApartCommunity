package kr.secondhand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.FileUtil;

public class DeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String> mapAjax = new HashMap<String, String>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num == null) {
			//로그인되지 않은 경우
			mapAjax.put("result","logout");
		}else {
			//로그인 된 경우
			int se_num = Integer.parseInt(request.getParameter("se_num"));
			SecondHandDAO dao = SecondHandDAO.getinstance();
			SecondHandVO db_secondhand = dao.getSecondHandDetail(se_num);
			//로그인한 회원번호와 작성자 회원번호 일치여부
			if(user_num != db_secondhand.getMem_num()) {
				//로그인한 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result", "wrongAccess");
			}else {
				//파일 정보 삭제
				dao.deleteFile(se_num);
				//파일삭제
				FileUtil.removeFile(request, db_secondhand.getFilename());
				mapAjax.put("result", "success");
			}			
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
