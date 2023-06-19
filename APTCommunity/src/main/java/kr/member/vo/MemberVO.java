package kr.member.vo;

import java.sql.Date;

public class MemberVO {

	private int mem_num; // 입주민 번호
	private String dongho;
	private int auth;// 회원등급(1:일반,9:관리)
	private String name;// 세대주
	private String passwd;// 패스워드
	private String phone;// 전화번호(010-1234-5678 형식으로 입력)
	private String email;// 이메일(test@test.com 형식으로 입력)
	private String carnum;// 차량 번호(02허 9756형식으로 입력)
	private Date reg_date; // 가입일
	private Date modify_date; // 정보 수정일

	// 비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		// 회원등급(auth): 2일반회원,9관리자
		if (auth > 0 && passwd.equals(userPasswd)) {
			return true;
		}
		return false;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getDongho() {
		return dongho;
	}

	public void setDongho(String dongho) {
		this.dongho = dongho;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarnum() {
		return carnum;
	}

	public void setCarnum(String carnum) {
		this.carnum = carnum;
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

}
