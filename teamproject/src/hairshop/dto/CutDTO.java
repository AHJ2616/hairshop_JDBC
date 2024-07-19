package hairshop.dto;

public class CutDTO {
	
	private String csname;
	private String ccutname;
	private String ccontents;
	private int csno;
	private int ccode;
	private int cprice;
	
public CutDTO() {} // basic constructor
	
	public CutDTO(int csno, String ccutname, int cprice, String ccontents) {
		super();
		this.csno = csno;
		this.ccutname = ccutname;
		this.cprice = cprice;
		this.ccontents = ccontents;
	}
	
	
	
	public String getCsname() {
		return csname;
	}
	public String getCcutname() {
		return ccutname;
	}
	public String getCcontents() {
		return ccontents;
	}
	public int getCsno() {
		return csno;
	}
	public int getCcode() {
		return ccode;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCsname(String csname) {
		this.csname = csname;
	}
	public void setCcutname(String ccutname) {
		this.ccutname = ccutname;
	}
	public void setCcontents(String ccontents) {
		this.ccontents = ccontents;
	}
	public void setCsno(int csno) {
		this.csno = csno;
	}
	public void setCcode(int ccode) {
		this.ccode = ccode;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	

}//class end
