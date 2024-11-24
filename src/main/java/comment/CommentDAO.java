package comment;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import board.BoardDTO;
import common.DBConnPool;

public class CommentDAO extends DBConnPool{

	// 댓글 개수
	public int Commentcount(String board_id) {
		int totalCount =0;
		String query = "SELECT COUNT(*) FROM comments "
				+ " WHERE board_id=? ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,board_id);
			rs = psmt.executeQuery();
			
			rs.next();
			totalCount = rs.getInt(1);				

		} catch (Exception e) {
			System.out.println("Commentcount에서 오류!");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	
	//댓글 넣기
	public List<CommentDTO> Commentlist(Map<String, Object> map,String board_id){
		List<CommentDTO> comment = new Vector<CommentDTO>();
		//쿼리
		String query = "select C.*, M.nickname "
				+ " from comments C "
				+ " inner join member M "
				+ "    on C.user_id=M.user_id "
				+ " where board_id=? ";
		
		// 내림차순 정렬
		query += " ORDER BY cm_id DESC ";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_id);
			rs = psmt.executeQuery();
			while(rs.next()) {
				CommentDTO cdto = new CommentDTO();
				cdto.setCm_id(rs.getString(1));
				cdto.setBoard_id(rs.getString(2));
				cdto.setUser_id(rs.getString(3));
				cdto.setContent(rs.getString(4));
				cdto.setCm_date(rs.getString(5));
				cdto.setNickname(rs.getString(6));
				comment.add(cdto);
			}
		} catch (Exception e) {
			System.out.println("Commentlist에서 오류");
			e.printStackTrace();
		}
		return comment;
	}
	
	//페이징
	public List<BoardDTO> boardPage(Map<String,Object> map){
		List<BoardDTO> board = new Vector<BoardDTO>();
			
		String query = " SELECT * FROM ( "
					+ " SELECT Tb.*, ROWNUM rNum FROM ( "
					+ " SELECT * FROM board ";
		if(map.get("searchWord") != null) {
				query +=" WHERE " + map.get("searchField")
					+ " LIKE '%" + map.get("searchWord") + "%' "
					+ " AND category='comment' ";
		}else {
			query += " WHERE category='comment' ";
		}
		query += " ORDER BY board_id DESC "
					+ " 	) Tb "
					+ " ) "
					+ " WHERE rNum BETWEEN ? AND ?";
			
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("start").toString());
			psmt.setString(2, map.get("end").toString());
			rs = psmt.executeQuery();
				
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
					
				dto.setBoard_id(rs.getString(1));
				dto.setUser_id(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setCategory(rs.getString(7));
					
				board.add(dto);
			}
		} catch (Exception e) {
				System.out.println("페이징 중 오류");
				e.printStackTrace();
		}
		return board;
	}
	
	
	// 게시물 카운트하는 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount =0;
		String query = "SELECT COUNT(*) FROM board ";
		if (map.get("searchWord")!=null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%"+map.get("searchWord")+"%' "
					+ " AND category='comment' ";
		}else {
			query += " WHERE category='comment' ";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		} catch (Exception e) {
			System.out.println("게시문 카운트 오류!");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	//댓글 달기
	public int cm(CommentDTO cdto) {
		int result = 0;
		String query = "INSERT INTO comments "
				+ " (cm_id, board_id, user_id, content) "
				+ " VALUES(cm_seq.nextval, ?, ?, ?)";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,cdto.getBoard_id());
			psmt.setString(2,cdto.getUser_id());
			psmt.setString(3,cdto.getContent());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("comment 삽입중에 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	// 댓글 뷰를 select 식별
	public CommentDTO cmView(String board_id) {
		CommentDTO cdto = new CommentDTO();
		String query = "select C.*, M.nickname "
				+ " from comments C "
				+ " inner join member M "
				+ "    on C.user_id=M.user_id "
				+ " where board_id=? ";
		
		// 인파라미터 설정
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_id);
			rs = psmt.executeQuery();
			
			//board_id의 결과는 하나만 나옴
			if (rs.next()) {
				cdto.setCm_id(rs.getString(1));
				cdto.setBoard_id(rs.getString(2));
				cdto.setUser_id(rs.getString(3));
				cdto.setContent(rs.getString(4));
				cdto.setCm_date(rs.getString(5));
				cdto.setNickname(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println("뷰에에 접근중 오류.");
			e.printStackTrace();
		}
		return cdto;
	}
}
