package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class BoardDAO extends DBConnPool{

	
	// 게시물 카운트하는 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount =0;
		String query = "SELECT COUNT(*) FROM board ";
		if (map.get("search")!=null) {
			query += " WHERE category "
					+ " LIKE '%"+map.get("search")+"%'";
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
	
	
	//게시글 가져오는 메서드
	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> board = new Vector<BoardDTO>();
		//쿼리
		String query = "SELECT * FROM board ";
		if (map.get("search")!=null) {
			query += " WHERE category "
					+ " LIKE '%"+map.get("search")+"%'";
		}
		// 내림차순 정렬
		query += " ORDER BY board_id DESC ";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				
				
				dto.setBoard_id(rs.getString(1));
				dto.setUser_id(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setVisitcount(rs.getInt(6));
				
				board.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시글 조회 오류");
			e.printStackTrace();
		}
		
		return board;
	}
	
	
	// 글쓰기 하고 DB 저장
	public int insertboard(BoardDTO dto) {
        int result = 0;
        try {
            String query = "INSERT INTO board ( "
                         + " board_id, user_id, title, content, category) "
                         + " VALUES ( "
                         + " seq_board_num.NEXTVAL,?,?,?,'list')";
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getUser_id());
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
}
