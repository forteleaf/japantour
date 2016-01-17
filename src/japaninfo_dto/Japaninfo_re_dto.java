package japaninfo_dto;

import java.sql.Date;

public class Japaninfo_re_dto {
	private int reinum;
	private int inum;
	private String id;
	private String comments;
	private Date regdate;
	private int ref;
	private int lev;
	private int step;
	public Japaninfo_re_dto(){}
	public Japaninfo_re_dto(int reinum, int inum, String id, String comments, Date regdate, int ref, int lev,
			int step) {
		super();
		this.reinum = reinum;
		this.inum = inum;
		this.id = id;
		this.comments = comments;
		this.regdate = regdate;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}
	public int getReinum() {
		return reinum;
	}
	public void setReinum(int reinum) {
		this.reinum = reinum;
	}
	public int getInum() {
		return inum;
	}
	public void setInum(int inum) {
		this.inum = inum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	
}
