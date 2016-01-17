package Msg_Dto;

public class MsgDto {
	private int msgnum;
	private String id;
	private String toid;
	private String title;
	private String contents;
	private String regdate;
	
	public MsgDto(){}

	public MsgDto(int msgnum, String id, String toid, String title, String contents, String regdate) {
		super();
		this.msgnum = msgnum;
		this.id = id;
		this.toid = toid;
		this.title = title;
		this.contents = contents;
		this.regdate = regdate;
	}

	public int getMsgnum() {
		return msgnum;
	}

	public void setMsgnum(int msgnum) {
		this.msgnum = msgnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getToid() {
		return toid;
	}

	public void setToid(String toid) {
		this.toid = toid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
