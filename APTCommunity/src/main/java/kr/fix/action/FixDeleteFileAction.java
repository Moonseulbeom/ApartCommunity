package kr.fix.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.util.FileUtil;

public class FixDeleteFileAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,String> mapAjax = new HashMap<String, String>();
		
		//로그인 되어있는지 체크, 아닐시 로그인폼으로 이동
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			mapAjax.put("result", "logout");
		}else {
			Integer user_auth = (Integer)session.getAttribute("user_auth");
			int fix_num = Integer.parseInt(request.getParameter("fix_num"));
			
			FixDAO dao = FixDAO.getInstance();
			FixVO db_fix = dao.getFix(fix_num);
			
			//현재 로그인한 회원번호와 작성자 회원번호 일치 여부 체크
			//관리자는 등급을 확인하여 패스
			if(user_num!= db_fix.getMem_num() && user_auth != 9) {
				//로그인한 회원번호와 작성자 회원번호 불일치
				mapAjax.put("result","wrongAccess");
			}else {
				//파일 정보 삭제
				dao.deleteFile(fix_num);
				//파일 삭제
				FileUtil.removeFile(request, db_fix.getFilename());
				mapAjax.put("result","success");
			}
		}

		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String  ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}



















