package leaf.dto;

import java.sql.Date;

public class ChatDto {
	private int no;
	private String id;
	private String msg;
	private Date time;

	public ChatDto() {
		// TODO Auto-generated constructor stub
	}

	public ChatDto(int no, String id, String msg, Date time) {
		super();
		this.no = no;
		this.id = id;
		this.msg = msg;
		this.time = time;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}