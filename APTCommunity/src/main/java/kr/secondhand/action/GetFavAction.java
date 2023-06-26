package kr.secondhand.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondhandFavVO;

public class GetFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//전송된 데이터 반환
		int se_num = Integer.parseInt(request.getParameter("se_num"));
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		//Integer se_num = (Integer)session.getAttribute("se_num");
		
		SecondHandDAO dao = SecondHandDAO.getinstance();
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("status", "noFav");
			mapAjax.put("count",dao.selectFavCount(se_num));
		}else {//로그인 된 경우
			SecondhandFavVO shFav = dao.selectFav(new SecondhandFavVO(se_num, user_num));
			if(shFav!=null) {
				//로그인한 회원이 해당 글에 좋아요 표시
				mapAjax.put("status", "yesFav");
				mapAjax.put("count",dao.selectFavCount(se_num));
			}else {
				//로그인한 회원이 해당 글에 좋아요 표시하지 않음
				mapAjax.put("status", "noFav");
				mapAjax.put("count", dao.selectFavCount(se_num));
			}
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		//JSP 경로 반환
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
