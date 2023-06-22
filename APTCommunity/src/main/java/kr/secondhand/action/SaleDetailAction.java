package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.StringUtil;

public class SaleDetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 번호
		int se_num = Integer.parseInt(request.getParameter("se_num"));
		SecondHandDAO dao = SecondHandDAO.getinstance();
		
		//레코드 반환받기
		SecondHandVO vo = dao.getSecondHand(se_num);
				
		//HTML 태그를 허용하지 않음
		vo.setTitle(StringUtil.useNoHtml(vo.getTitle()));
				
		//HTML 태그를 허용하지 않으면서 줄바꿈처리
		vo.setContent(StringUtil.useBrNoHtml(vo.getContent()));
			
		request.setAttribute("vo", vo);
				
		//JSP 경로반환
		return "/WEB-INF/views/secondhand/seSaleDetail.jsp";
	}

}
