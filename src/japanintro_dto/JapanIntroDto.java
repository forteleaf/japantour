package japanintro_dto;

import java.sql.Date;

public class JapanIntroDto {
	private int num;
	private String id;
	private String title;
	private String content;
	private Date time;
	private int localnum;
	
	public JapanIntroDto (){}

	public JapanIntroDto(int num, String id, String title, String content, Date time, int localnum) {
		super();
		this.num = num;
		this.id = id;
		this.title = title;
		this.content = content;
		this.time = time;
		this.localnum = localnum;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
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
	
}
