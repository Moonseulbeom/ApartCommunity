package kr.mypage.vo;

import java.sql.Date;

//내가 쓴 글
public class MyPageVO {
	private String category;//카테고리(자유게시판,하자보수,일대일문의,중고거래)
	private int num;//글번호
	private int mem_num;//회원번호
	private String title;//제목
	private Date reg_date;//작성일
	private int cnt;//댓글 수
	
	private String dongho;//회원아이디

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getDongho() {
		return dongho;
	}

	public void setDongho(String dongho) {
		this.dongho = dongho;
	}
}
