package kr.fix.dao;


public class FixDAO {
	//싱글턴 패턴
	private static FixDAO instance = new FixDAO();
	public static FixDAO getInstance() {
		return instance;
	}
	private FixDAO() {}
	
	//하자보수 글쓰기
	//하자보수 내가 쓴 글목록
	//하자보수 글목록(관리자)
}
