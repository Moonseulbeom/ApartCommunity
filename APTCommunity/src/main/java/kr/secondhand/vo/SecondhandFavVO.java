package kr.secondhand.vo;

public class SecondhandFavVO {
	private int fav_num; //찜 번호
	private int mem_num; //회원번호
	private int se_num;  //중고거래(게시글) 번호
	
	private String dongho;

	public int getFav_num() {
		return fav_num;
	}

	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
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

	public String getDongho() {
		return dongho;
	}

	public void setDongho(String dongho) {
		this.dongho = dongho;
	}
}
