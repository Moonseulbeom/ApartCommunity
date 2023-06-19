package kr.booking.dao;

public class BookingDAO {
	//싱글턴 패턴
	private static BookingDAO instance = new BookingDAO();
	public static BookingDAO getInstance() {
		return instance;
	}
	private BookingDAO() {}
	
}
