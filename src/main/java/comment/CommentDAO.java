package comment;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import board.BoardDTO;
import common.DBConnPool;

public class CommentDAO extends DBConnPool{

	// 댓글 개수
	public int Commentcount(Map<String, Object> map, String board_id) {
		int totalCount =0;
		String query = "SELECT COUNT(*) FROM comments "
				+ " WHERE board_id=? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,board_id);
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시문 카운트 오류!");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	//댓글 넣기
	public List<CommentDTO> Commentlist(Map<String, Object> map){
		List<CommentDTO> comment = new Vector<CommentDTO>();
		//쿼리
		String query = "SELECT * FROM comments "
				+ " WHERE category='comment' ";
		
		// 내림차순 정렬
		query += " ORDER BY cm_id DESC ";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CommentDTO cdto = new CommentDTO();
				
				cdto.setCm_id(rs.getString(1));
				cdto.setBoard_id(rs.getString(2));
				cdto.setUser_id(rs.getString(3));
				cdto.setContent(rs.getString(4));
				cdto.setCm_date(rs.getString(5));
				
				comment.add(cdto);
			}
		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		}
		
		return comment;
	}
}
