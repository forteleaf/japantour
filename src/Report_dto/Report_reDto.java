package Report_dto;

import java.util.Date;

public class Report_reDto {
	private int renum;
	private int rnum;
	private String id;
	private Date regdate;
	private String comments;
	private int ref;
	private int lev;
	private int step;

	public Report_reDto(){}

	public Report_reDto(int renum, int rnum, String id, Date regdate, String comments, int ref, int lev, int step) {
		super();
		this.renum = renum;
		this.rnum = rnum;
		this.id = id;
		this.regdate = regdate;
		this.comments = comments;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}

	public int getRenum() {
		return renum;
	}

	public void setRenum(int renum) {
		this.renum = renum;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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