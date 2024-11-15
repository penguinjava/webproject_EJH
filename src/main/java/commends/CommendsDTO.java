package commends;

public class CommendsDTO {
	// commends 필드 생성
	private String id;
	private int no;
	private String content;
	
	//get&set 설정
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
