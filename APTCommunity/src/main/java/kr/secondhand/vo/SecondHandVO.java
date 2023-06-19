package kr.secondhand.vo;

import java.sql.Date;

public class SecondHandVO {
	private int se_num; //게시글번호
	private int mem_num; //입주민번호
	private int division; //판매및구매구분번호
	private String title; //글제목
	private String content; //글내용
	private Date reg_date; //글작성일
	private Date modify_date; //글수정일
	private String filename; //파일이름
	private String ip; //아이피
	
	private String dongho;//회원아이디(동호수)
	
	public String getDongho() {
		return dongho;
	}
	public void setDongho(String dongho) {
		this.dongho = dongho;
	}
	
	public int getSe_num() {
		return se_num;
	}
	public void setSe_num(int se_num) {
		this.se_num = se_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getDivision() {
		return division;
	}
	public void setDivision(int division) {
		this.division = division;
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
