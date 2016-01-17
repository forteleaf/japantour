package japaninfo_dto;

import java.sql.Date;

public class Japaninfo_dto {
	private int inum;
	private	String id;
	private String title;
	private String content;
	private Date time;
	private int localnum;
	private int likecnt;
	private String orgfilename;
	private String savefilename;
	private long filesize;
	public Japaninfo_dto(){}
	public Japaninfo_dto(int inum, String id, String title, String content, Date time, int localnum, int likecnt,
			String orgfilename, String savefilename, long filesize) {
		super();
		this.inum = inum;
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
}
