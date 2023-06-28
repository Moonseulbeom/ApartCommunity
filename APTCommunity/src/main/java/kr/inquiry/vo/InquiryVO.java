package kr.inquiry.vo;

import java.sql.Date;

public class InquiryVO {
	private int in_num; //1:1문의번호
	private int mem_num; //회원번호
	private String title; //제목
	private String content; //내용
	private Date reg_date; //등록일
	private Date modify_date; //수정일
	private String filename; //파일명
	private String ip; //ip주소
	private String dongho;//회원 아이디
	
	private int check;//댓글 존재 확인
	
	private int cnt;
	

	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	
	public String getDongho() {
		return dongho;
	}
	public void setDongho(String dongho) {
		this.dongho = dongho;
	}
	public int getIn_num() {
		return in_num;
	}
	public void setIn_num(int in_num) {
		this.in_num = in_num;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	
}
