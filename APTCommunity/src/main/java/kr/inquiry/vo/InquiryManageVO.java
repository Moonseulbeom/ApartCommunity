package kr.inquiry.vo;

import java.sql.Date;

public class InquiryManageVO {
	private int re_num;//댓글번호
	private int in_num;//부모글번호
	private int mem_num;//회원번호
	private String content;//댓글내용
	private Date reg_date;//댓글등록일
	private Date modify_date;//댓글수정일
	private String ip;//IP주소
	
	private String dongho;//회원 아이디
	private int auth; //회원 등급
	
	private int count;//댓글 개수
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public int getRe_num() {
		return re_num;
	}

	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}

	public int getIn_num() {
		return in_num;
	}

	public void setIn_num(int in_num) {
		this.in_num = in_num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDongho() {
		return dongho;
	}

	public void setDongho(String dongho) {
		this.dongho = dongho;
	}
	
	
}
