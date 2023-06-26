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

public class WriteFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {//로그인 되지 않은 경우
			mapAjax.put("result", "logout");
		}else {//로그인 된 경우
			//전송된 데이터 인코딩 처리
			request.setCharacterEncoding("utf-8");
			//전송된 데이터 반환
			int se_num = Integer.parseInt(request.getParameter("se_num"));
			SecondHandDAO dao = SecondHandDAO.getinstance();
			SecondhandFavVO favVO = new SecondhandFavVO();
			favVO.setSe_num(se_num);
			favVO.setMem_num(user_num);
			//좋아요 등록 여부 체크
			SecondhandFavVO db_fav = dao.selectFav(favVO);
			if(db_fav!=null) {//좋아요 등록 O
				//좋아요 삭제 처리
				dao.deleteFav(db_fav.getFav_num());
				mapAjax.put("result", "success");
				mapAjax.put("status", "noFav");
				mapAjax.put("count", dao.selectFavCount(se_num));
			}else {//좋아요 등록 X
				//좋아요 등록 처리
				dao.insertFav(favVO);
				mapAjax.put("result", "success");
				mapAjax.put("status", "yesFav");
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
