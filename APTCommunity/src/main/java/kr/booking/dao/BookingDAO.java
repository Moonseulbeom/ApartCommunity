package kr.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.booking.vo.BookingVO;
import kr.booking.vo.Room_infoVO;
import kr.util.DBUtil;

public class BookingDAO {
	//싱글턴 패턴
	private static BookingDAO instance = new BookingDAO();
	public static BookingDAO getInstance() {
		return instance;
	}
	private BookingDAO() {}
	
	//시설 정보 리스트 불러오기 (인자:시설이름번호)
	public List<Room_infoVO> getRoomInfoList(String room_Name) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Room_infoVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM room_info "
				+ "WHERE room_name=? "
				+ "ORDER BY room_num ASC ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_Name);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Room_infoVO>();
			while (rs.next()) {
				Room_infoVO ri = new Room_infoVO();
				ri.setRoom_num(rs.getInt("room_num"));
				ri.setRoom_type(rs.getString("room_type"));
				ri.setRoom_name(rs.getString("room_Name"));
				ri.setBk_status(rs.getInt("bk_status"));
				ri.setTotal_mem(rs.getInt("total_mem"));
				
				list.add(ri);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
