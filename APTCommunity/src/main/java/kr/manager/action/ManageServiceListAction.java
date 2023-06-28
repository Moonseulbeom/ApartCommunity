package kr.manager.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.booking.dao.BookingDAO;
import kr.booking.vo.BookingVO;
import kr.booking.vo.Room_infoVO;
import kr.controller.Action;
import kr.fix.dao.FixDAO;
import kr.fix.vo.FixVO;
import kr.inquiry.dao.InquiryDAO;
import kr.inquiry.vo.InquiryVO;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;
import kr.util.PageUtil;

public class ManageServiceListAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		Integer user_auth = (Integer)session.getAttribute("user_auth");
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		
		if(user_num == null || user_auth < 9) {
			request.setAttribute("notice_msg", "잘못된 접근입니다.");
			request.setAttribute("notice_url", request.getContextPath()+"/main/main.do");
			return "/WEB-INF/views/common/alert_singleView.jsp";
		}
		
		String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
        
		String keyfield = request.getParameter("mem_select");
		String keyword = request.getParameter("keyword");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		if(keyword != null) {//search한 경우
			request.setCharacterEncoding("utf-8");
			
			int count = dao.getMemberCountByAdmin(keyfield, keyword);
			PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, count, "manageMain.do");
			
			List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
			mapAjax.put("list", list);
			mapAjax.put("page", page.getPage());
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			request.setAttribute("count", count);
			request.setAttribute("ajaxData", ajaxData);
			//JSP 경로 반환
			return "/WEB-INF/views/common/ajax_view.jsp";
		}
		
		//회원 리스트
		int count = dao.getMemberCountByAdmin(keyfield, keyword);
		PageUtil page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, count, "manageMain.do");
		List<MemberVO> list = dao.getListMemberByAdmin(page.getStartRow(), page.getEndRow(), keyfield, keyword);
		
		//1:1문의 리스트
		InquiryDAO in_dao = InquiryDAO.getInstance(); 
		int in_count = in_dao.getInquiryCount(keyfield, keyword);
		PageUtil in_page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, in_count, "manageMain.do");	
		List<InquiryVO> inquiry_list = in_dao.getListInquiry(in_page.getStartRow(), in_page.getEndRow(), keyfield, keyword);
		
		//하자보수 리스트
		FixDAO fix_dao = FixDAO.getInstance();
		int fix_count = fix_dao.getFixCount(keyfield, keyword);
		PageUtil fix_page = new PageUtil(keyfield, keyword, Integer.parseInt(pageNum), 10, 10, fix_count, "manageMain.do");	
		List<FixVO> fix_list = fix_dao.getListFix(fix_page.getStartRow(), fix_page.getEndRow(), keyfield, keyword);
		
		//예약 리스트 선택
		String room_name = request.getParameter("room_name");
		String room_num = request.getParameter("room_num");
		String bk_date = request.getParameter("bk_date");

		if(room_name != null && bk_date == null) {
			BookingDAO book_dao = BookingDAO.getInstance();
			List<Room_infoVO> bookinfo_list = book_dao.getRoomInfoList(room_name);
			mapAjax.put("bookinfo_list", bookinfo_list);
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			
			request.setAttribute("ajaxData", ajaxData);
			//JSP 경로 반환
			return "/WEB-INF/views/common/ajax_view.jsp";
		}
   
		//예약 삭제&예약 막기
		String book_check = request.getParameter("book_check");
		if(book_check != null) {
			//System.out.println(bk_date);
			//System.out.println(room_type);
			//System.out.println(book_check);
			BookingDAO book_dao = BookingDAO.getInstance();
				book_dao.deleteBookingFromDate(Integer.parseInt(room_num), bk_date);
			if(book_check.equals("1")) {
				//관리자 예약으로 시설 막기
				BookingVO book = new BookingVO();
				book.setRoom_num(Integer.parseInt(room_num));
				book.setMem_num(user_num);
				book.setBook_mem(1);
				book.setBk_status(1);
				book.setBk_date(bk_date);
				book.setStart_time("9:00");
				book.setEnd_time("22:00");
				
				book_dao.insertMemberBooking(book);
				mapAjax.put("reuslt", "success");
				ObjectMapper mapper = new ObjectMapper();
				String ajaxData = mapper.writeValueAsString(mapAjax);
				
				request.setAttribute("ajaxData", ajaxData);
				//JSP 경로 반환
				return "/WEB-INF/views/common/ajax_view.jsp";
			}
		}
		
		//예약 리스트 확인
		if(room_num != null) {
			//System.out.println(room_type);
			BookingDAO book_dao = BookingDAO.getInstance();
			List<BookingVO> book_list = book_dao.getManageBookList(Integer.parseInt(room_num),bk_date);
			int check_auth = 1;
			for(BookingVO book : book_list) {
				String dongho = book.getDongho();
				MemberVO mem = dao.checkMember(dongho);
				if(mem.getAuth() == 9) {
					check_auth = 9;
				}
			}
			System.out.println(check_auth);
			mapAjax.put("check_auth", check_auth);
			mapAjax.put("book_list", book_list);
			ObjectMapper mapper = new ObjectMapper();
			String ajaxData = mapper.writeValueAsString(mapAjax);
			
			request.setAttribute("ajaxData", ajaxData);
			//JSP 경로 반환
			return "/WEB-INF/views/common/ajax_view.jsp";
		}  
		
		
		request.setAttribute("count", count);
		request.setAttribute("page", page.getPage());
		request.setAttribute("list", list);//회원 리스트
		
		request.setAttribute("in_page", in_page.getPage());
		request.setAttribute("in_count", in_count);
		request.setAttribute("inquiry_list", inquiry_list);//하자보수 리스트
		
		request.setAttribute("fix_page", fix_page.getPage());
		request.setAttribute("fix_count", fix_count);
		request.setAttribute("fix_list", fix_list);//1:1문의 리스트
		
		//예약 리스트
		
		return "/WEB-INF/views/manager/manage-serviceList.jsp";
	}

}
