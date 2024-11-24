package likes;

public class LikeDTO {
	
	private String board_id;
	private String user_ie;
	private int likes;
	
	public String getBoard_id() {
		return board_id;
	}
	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public String getUser_ie() {
		return user_ie;
	}
	public void setUser_ie(String user_ie) {
		this.user_ie = user_ie;
	}
	public int getLike() {
		return likes;
	}
	public void setLike(int likes) {
		this.likes = likes;
	}
	
	
}
