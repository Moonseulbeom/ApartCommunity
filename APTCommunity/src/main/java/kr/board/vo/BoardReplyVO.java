package kr.board.vo;

import java.sql.Date;

public class BoardReplyVO {
	private int re_num;//댓글번호
	private int mem_num;//회원번호
	private int board_num;//글번호
	private String content;//댓글내용
	private Date reg_date;//등록일
	private Date modify_date;//수정일
	private String ip;//아이피
	private String dongho;//동호수(회원아이디)
	
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
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
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
