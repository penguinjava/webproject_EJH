package board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;
import file.FileDTO;

public class BoardDAO extends DBConnPool{

	
	// 게시물 카운트하는 메서드
	public int selectCount(Map<String, Object> map) {
		int totalCount =0;
		String query = "SELECT COUNT(*) FROM board ";
		if (map.get("searchWord")!=null) {
			query += " WHERE category= " + map.get("searchField")
					+ " AND LIKE '%"+map.get("searchWord")+"%'";
		}else {
			query += " WHERE category='board' ";
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
	
	
	//게시글 검색
	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> board = new Vector<BoardDTO>();
		//쿼리
		String query = "SELECT * FROM board ";
		if (map.get("searchWord")!=null) {
			query += " WHERE " + map.get("searchField")
					+ " LIKE '%"+map.get("searchWord")+"%'";
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
	public int insertboard(BoardDTO bdto) {
        int result = 0;
        try {
            String query1 = "INSERT INTO board ( "
                         + " board_id, user_id, title, content, category) "
                         + " VALUES ( "
                         + " board_seq.nextval,?,?,?,?)";
            
            
            psmt = con.prepareStatement(query1);
            psmt.setString(1, bdto.getUser_id());
            psmt.setString(2, bdto.getTitle());
            psmt.setString(3, bdto.getContent());
            psmt.setString(4, bdto.getCategory());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }
	
	
	// 글쓰기 뷰를 select 식별
	public BoardDTO listView(String board_id) {
		BoardDTO dto = new BoardDTO();
		String query = "select B.*, M.nickname "
				+ " from board B inner join member M "
				+ "    on B.user_id=M.user_id "
				+ " where board_id=? ";
		
		// 인파라미터 설정
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_id);
			rs = psmt.executeQuery();
			
			//board_id의 결과는 하나만 나옴
			if (rs.next()) {
				dto.setBoard_id(rs.getString(1));
				dto.setUser_id(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getString(5));
				dto.setVisitcount(rs.getInt(6));
				dto.setCategory(rs.getString(7));
				dto.setNickname(rs.getString(8));
			}
		} catch (Exception e) {
			System.out.println("뷰에에 접근중 오류.");
			e.printStackTrace();
		}
		return dto;
	}
	
	
	//조회수 늘리기
	public void updateVisit(String board_id	) {
		String query = "UPDATE board SET "
				+ " visitcount=visitcount+1 "
				+ " WHERE board_id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_id);
			psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("조회수 늘리기중 오류");
			e.printStackTrace();
		}
	}
	
	//게시판 삭제하기
	public int deleteList(String board_id) {
		int result = 0;
		
		//삭제는 제약조건이 걸려있어서 쿼리부터 예외처리한다.
		try {
			String query = "DELETE FROM board "
					+ "WHERE board_id=?";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_id);
			
			//삭제 실행
			result = psmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("게시물 삭제중 오류");
			e.printStackTrace();
		}
		return result;
	}
	
	
	//게시판 수정하기
	public int updateList(BoardDTO dto) {
		int result = 0;
		String query = "UPDATE board "
				+ " SET title=?, content=? "
				+ " WHERE board_id=? and user_id=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());			
			psmt.setString(2, dto.getContent());			
			psmt.setString(3, dto.getBoard_id());			
			psmt.setString(4, dto.getUser_id());
			
			// 업데이트 실행
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("수정중 오류 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//페이징
	public List<BoardDTO> boardPage(Map<String,Object> map){
		List<BoardDTO> board = new Vector<BoardDTO>();
			
		String query = " SELECT * FROM ( "
					+ " SELECT Tb.*, ROWNUM rNum FROM ( "
					+ " SELECT * FROM board ";
		if(map.get("searchWord") != null) {
				query +=" WHERE category=" + map.get("searchFild")
					+ " AND title "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}else {
			query += " WHERE category='board' ";
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
}
