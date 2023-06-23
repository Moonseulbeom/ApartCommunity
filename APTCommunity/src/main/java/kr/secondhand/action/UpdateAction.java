package kr.secondhand.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.secondhand.dao.SecondHandDAO;
import kr.secondhand.vo.SecondHandVO;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 체크여부
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int se_num = Integer.parseInt(multi.getParameter("se_num"));
		String filename = multi.getFilesystemName("filename");
		
		//수정전 데이터 변환
		SecondHandDAO dao = SecondHandDAO.getinstance();
		SecondHandVO db_secondhand = dao.getSecondHandDetail(se_num);
		
		//로그인한 회원번호와 작성자 회원번호 일치여부 체크
		if(user_num != db_secondhand.getMem_num()) {
			//불일치 경우
			FileUtil.removeFile(request, filename);
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//일치 경우
		SecondHandVO vo = new SecondHandVO();
		vo.setSe_num(se_num);
		vo.setTitle(multi.getParameter("title"));
		vo.setContent(multi.getParameter("content"));
		vo.setIp(request.getRemoteAddr());
		vo.setFilename(filename);
		
		//DAO에 있는 updateSecondhand에 데이터 넘겨주기
		dao.updateSecondhand(vo);
		
		//새 파일로 교체할 때 원래 파일 제거(트래쉬메모리 방지)
		if(filename != null) {
			
			FileUtil.removeFile(request, db_secondhand.getFilename());
		}
		//division 변수 만들어서 저장해두고 이용하기/염유진
		if(db_secondhand.getDivision()==1) {
			return "redirect:/secondhand/seSaleDetail.do?se_num="+se_num;
		}
		
		return "redirect:/secondhand/seBuyDetail.do?se_num="+se_num;
	}

}
