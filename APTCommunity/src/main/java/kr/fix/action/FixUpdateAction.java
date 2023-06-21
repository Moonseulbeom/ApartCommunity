package kr.fix.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.util.FileUtil;

public class FixUpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//로그인 되어있는지 체크, 아닐시 로그인폼으로 이동
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if (user_num==null) {
			request.setAttribute("notice_msg", "로그인이 필요한 서비스입니다");
			request.setAttribute("notice_url", request.getContextPath()+"/member/loginForm.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		//로그인 된 경우
		MultipartRequest multi = FileUtil.createFile(request); //이미 upload폴더에 들어가있음
		int fix_num = Integer.parseInt(multi.getParameter("fix_num"));
		String filename = multi.getFilesystemName("filename");
		
		FixDAO dao = FixDAO.getInstance();
		//수정전 데이터 반환
		FixVO db_fix = dao.getFix(fix_num);
		
		//로그인한 회원번호와 작성자 회원번호 일치 여부 체크
		if(user_num != db_fix.getMem_num()) {
			//로그인한 회원번호와 작성자 회원번호 불일치
			FileUtil.removeFile(request, filename); //잘못 업로드된거 지우기
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		//로그인한 회원번호와 작성자 회원번호가 일치
		FixVO fix = new FixVO();
		fix.setFix_num(fix_num);
		fix.setTitle(multi.getParameter("title"));
		fix.setContent(multi.getParameter("content"));
		fix.setIp(request.getRemoteAddr());
		fix.setFilename(filename);
		
		//DB 데이터 수정하기
		dao.updateFix(fix);
		
		//새 파일로 교체할 때 원래 파일 제거
		if(filename!=null) {
			FileUtil.removeFile(request, db_fix.getFilename());
		}
		
		
		return "redirect:/fix/fixDetail.do?fix_num="+fix_num;
	}

}
