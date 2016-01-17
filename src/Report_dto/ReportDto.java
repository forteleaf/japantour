package Report_dto;

import java.sql.Date;

public class ReportDto {
	private int rnum;
	private String id;
	private String title;
	private String content;
	private Date time;
	private int localnum;
	private int likecnt;
	private String orgfilename;
	private String savefilename;
	private long filesize;

	public ReportDto(){}

	public ReportDto(int rnum, String id, String title, String content, Date time, int localnum, int likecnt,
			String orgfilename, String savefilename, long filesize) {
		super();
		this.rnum = rnum;
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.localnum = localnum;
		this.likecnt = likecnt;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.filesize = filesize;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getLocalnum() {
		return localnum;
	}

	public void setLocalnum(int localnum) {
		this.localnum = localnum;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	public String getOrgfilename() {
		return orgfilename;
	}

	public void setOrgfilename(String orgfilename) {
		this.orgfilename = orgfilename;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	

	@Override
	public String toString() {
		return "ReportDto [rnum=" + rnum + ", id=" + id + ", title=" + title + ", content=" + content + ", time=" + time
				+ ", localnum=" + localnum + ", likecnt=" + likecnt + ", orgfilename=" + orgfilename + ", savefilename="
				+ savefilename + ", filesize=" + filesize + "]";
	}

}

