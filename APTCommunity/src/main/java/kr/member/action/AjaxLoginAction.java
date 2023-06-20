package kr.member.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class AjaxLoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//전송된 데이터 인코딩 처리
        request.setCharacterEncoding("utf-8");

		//전송된 데이터 반환
        String dongho = request.getParameter("dongho");
        String passwd = request.getParameter("passwd");

        MemberDAO dao = MemberDAO.getInstance();
        MemberVO member = dao.checkMember(dongho);
        boolean check = false;
		
        Map<String,String> mapAjax = new HashMap<String,String>();
        if (member != null) {
            check = member.isCheckedPassword(passwd);
        }

        if (check) {
            HttpSession session = request.getSession();
            
            session.setAttribute("user_num", member.getMem_num());
            session.setAttribute("user_dongho", member.getDongho());
            session.setAttribute("user_auth", member.getAuth());
            mapAjax.put("result", "success");
        }else {
        	mapAjax.put("result", "failure");
        }

		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		//key와 value의 쌍으로 되어 있는 Map 데이터를
		//JSON 형식의 문자열 데이터로 변환 후 반환
        request.setAttribute("ajaxData", ajaxData);

        return "/WEB-INF/views/common/ajax_view.jsp";
    }

}
