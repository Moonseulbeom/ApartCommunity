package kr.secondhand.vo;

import java.sql.Date;

public class SecondhandReplyVO {
	private int re_num;//댓글번호
	private int mem_num;//
	private int se_num;
	private String content;
	private Date reg_date;
	private Date modify_date;
	private String ip;
	private String dongho;
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getSe_num() {
		return se_num;
	}
	public void setSe_num(int se_num) {
		this.se_num = se_num;
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
