package test.dto;

public class CommentsDto {
	private int num;
	private String id;
	private String comments;
	
	public CommentsDto() {}
	
	public CommentsDto(int num, String id, String comments) {
		super();
		this.num = num;
		this.id = id;
		this.comments = comments;
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
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	

}
