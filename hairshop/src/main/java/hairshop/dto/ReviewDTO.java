package hairshop.dto;

import java.sql.Date;

public class ReviewDTO {
	
	private int rno;
	private Date rdate;
	private String rwriter;
	private String rcontents;
	private int rdesignerNum;
	
	public int getRno() {
		return rno;
	}
	public Date getRdate() {
		return rdate;
	}
	public String getRwriter() {
		return rwriter;
	}
	public String getRcontents() {
		return rcontents;
	}
	public int getRdesignerNum() {
		return rdesignerNum;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public void setRwriter(String rwriter) {
		this.rwriter = rwriter;
	}
	public void setRcontents(String rcontents) {
		this.rcontents = rcontents;
	}
	public void setRdesignerNum(int rdesignernum) {
		this.rdesignerNum = rdesignernum;
	}
	
}
