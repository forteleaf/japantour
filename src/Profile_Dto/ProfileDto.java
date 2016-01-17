package Profile_Dto;

public class ProfileDto {
	private int pnum;
	private String id;
	private String comments;
	private String orgfilename;
	private String savefilename;
	private long filesize;
	
	public ProfileDto(){}

	public ProfileDto(int pnum, String id, String comments, String orgfilename, String savefilename, long filesize) {
		super();
		this.pnum = pnum;
		this.id = id;
		this.comments = comments;
		this.orgfilename = orgfilename;
		this.savefilename = savefilename;
		this.filesize = filesize;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
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
