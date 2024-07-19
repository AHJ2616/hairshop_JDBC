package hairshop.dto;

public class ShopDTO {

	private String sname;
	private String slocation;
	private int sno;
	private String sdesigner;
	private String sopen;
	private String sclose;
	
	//constructor
		public ShopDTO() {} // basic constructor
		
		public ShopDTO(String sname, String slocation, int sno, String sdesigner, String sopen, String sclose) {
			super();
			this.sname = sname;
			this.slocation = slocation;
			this.sno = sno;
			this.sdesigner = sdesigner;
			this.sopen = sopen;
			this.sclose = sclose;
		}// customized constructor
	
	
	public String getSname() {
		return sname;
	}
	public String getSlocation() {
		return slocation;
	}
	public int getSno() {
		return sno;
	}
	public String getSdesigner() {
		return sdesigner;
	}
	public String getSopen() {
		return sopen;
	}
	public String getSclose() {
		return sclose;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setSlocation(String slocation) {
		this.slocation = slocation;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public void setSdesigner(String sdesigner) {
		this.sdesigner = sdesigner;
	}
	public void setSopen(String sopen) {
		this.sopen = sopen;
	}
	public void setSclose(String sclose) {
		this.sclose = sclose;
	}
	
}// class end
