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
	
	//(유저)시설 정보 리스트 불러오기 (인자:시설이름번호)
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
				Room_infoVO room = new Room_infoVO();
				room.setRoom_num(rs.getInt("room_num"));
				room.setRoom_type(rs.getString("room_type"));
				room.setRoom_name(rs.getString("room_Name"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setTotal_mem(rs.getInt("total_mem"));
				
				list.add(room);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//(유저)시설(방) 하나의 모든 정보 얻어오기 
	public Room_infoVO getOneRoomInfo(int room_num) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		Room_infoVO room = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM room_info "
				+ "WHERE room_num=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room_num);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				room = new Room_infoVO();
				room.setRoom_num(rs.getInt("room_num"));
				room.setRoom_type(rs.getString("room_type"));
				room.setRoom_name(rs.getString("room_Name"));
				room.setRoom_status(rs.getInt("room_status"));
				room.setTotal_mem(rs.getInt("total_mem"));
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return room;
	}
	
	//선택한 날짜가 예약된 시간이 있는지 확인하기
	public List<String> getTimeList(int room_num, String bk_date) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		List<String> list = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT b.start_time||' ~ '||b.end_time AS time "
				+ "FROM booking b JOIN room_info r ON b.room_num = r.room_num "
				+ "WHERE b.bk_status = 1 AND r.room_num=? AND b.bk_date=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, room_num);
			pstmt.setString(2, bk_date);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<String>();
			while (rs.next()) {
				String time = rs.getString("time");
				
 				list.add(time);
			}
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	//(유저) 시설 예약 하기
	public void insertMemberBooking(BookingVO booking) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "INSERT INTO booking VALUES(booking_seq.nextval, ?, ?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, booking.getRoom_num());
			pstmt.setInt(2, booking.getMem_num());
			pstmt.setInt(3, booking.getBook_mem());
			pstmt.setInt(4, booking.getBk_status());
			pstmt.setString(5, booking.getBk_date());
			pstmt.setString(6, booking.getStart_time());
			pstmt.setString(7, booking.getEnd_time());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//(관리자) 모든 시설 리스트 불러오기
	//(관리자) 시설 비활성화 하기
	
	
	
	
	
	
	
	
	
	
}
