package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글번호
				int in_num = Integer.parseInt(request.getParameter("in_num"));
				InquiryDAO dao = InquiryDAO.getInstance();
				InquiryVO inquiry = dao.getInquiry(in_num);
				
				//HTML 태그를 허용하지 않음
				inquiry.setTitle(StringUtil.useNoHtml(inquiry.getTitle()));
				//HTML 태그를 허용하지 않으면서 줄바꿈 처리
				inquiry.setContent(StringUtil.useBrNoHtml(inquiry.getContent()));
				
				request.setAttribute("inquiry", inquiry);
				
				return "/WEB-INF/views/inquiry/detail.jsp";
			}

		}