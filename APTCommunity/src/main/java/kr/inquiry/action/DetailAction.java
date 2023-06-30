package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				HttpSession session = request.getSession();
				Integer user_num = (Integer)session.getAttribute("user_num");
				Integer user_auth = (Integer)session.getAttribute("user_auth");
				
				if (user_num==null) {
					request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
					request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
					return "/WEB-INF/views/common/alert_singleView.jsp";
				}
		
				//글번호
				int in_num = Integer.parseInt(request.getParameter("in_num"));
				InquiryDAO dao = InquiryDAO.getInstance();
				InquiryVO inquiry = dao.getInquiry(in_num);
				
				//작성자가 아니거나, 관리자가 아닐경우에 리스트목록으로
				if (user_num != inquiry.getMem_num() && user_auth != 9) {
					request.setAttribute("notice_msg", "조회 권한이 없습니다.");
					request.setAttribute("notice_url", request.getContextPath()+"/inquiry/list.do");
					return "/WEB-INF/views/common/alert_singleView.jsp";
				}
				
				//HTML 태그를 허용하지 않음
				inquiry.setTitle(StringUtil.useNoHtml(inquiry.getTitle()));
				//HTML 태그를 허용하지 않으면서 줄바꿈 처리
				inquiry.setContent(StringUtil.useBrNoHtml(inquiry.getContent()));
				
				request.setAttribute("inquiry", inquiry);
				
				return "/WEB-INF/views/inquiry/detail.jsp";
			}

		}