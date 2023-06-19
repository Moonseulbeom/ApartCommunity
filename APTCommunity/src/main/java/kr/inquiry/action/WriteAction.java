package kr.inquiry.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;


import kr.controller.Action;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.util.FileUtil;

public class WriteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 된 경우
				MultipartRequest multi = 
						FileUtil.createFile(request);
				//자바빈(VO) 생성
				InquiryVO inquiry = new InquiryVO();
				inquiry.setTitle(
						multi.getParameter("title"));
				inquiry.setContent(
					  multi.getParameter("content"));
				inquiry.setIp(request.getRemoteAddr());
				inquiry.setFilename(
						multi.getFilesystemName(
								        "filename"));
				
				InquiryDAO dao = InquiryDAO.getInstance();
				dao.insertInquiry(inquiry);
				//JSP 경로 반환
				return "/WEB-INF/views/inquiry/write.jsp";
			}

		}