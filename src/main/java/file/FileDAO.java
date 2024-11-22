package file;


import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import board.BoardDTO;
import common.DBConnPool;

public class FileDAO extends DBConnPool{
	
	// 글쓰기 하고 DB 저장
	public int insertboard(BoardDTO bdto,FileDTO fdto) {
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
            
    		if(result == 1) {
        		String query2 = " SELECT board_seq.CURRVAL "
        				+ " FROM board dual";
        		psmt = con.prepareStatement(query2);
        		rs = psmt.executeQuery();
        		
        		if(rs.next()) {
        			fdto.setBoard_id(rs.getString(1));
        		}
    		}
        }
        catch (Exception e) {
            System.out.println("게시물 입력 중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }	
	
	//파일 저장
	public int saveFile(FileDTO fdto) {
		int result = 0;
		String query1 = "INSERT INTO files "
				+ " (file_id, board_id , ofile, "
				+ " sfile) "
				+ " VALUES(file_seq.nextval,?,?,?) ";
		
		try {
			psmt = con.prepareStatement(query1);
			psmt.setString(1, fdto.getBoard_id());
			psmt.setString(2, fdto.getOfile());
			psmt.setString(3, fdto.getSfile());
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("파일 업로드중 오류");
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
				query +=" WHERE category= " + map.get("searchFild")
					+ " AND title "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}else {
			query += " WHERE category='file' ";
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
			query += " WHERE category= " + map.get("searchField")
					+ " AND LIKE '%"+map.get("searchWord")+"%'";
		}else {
			query += " WHERE category='file' ";
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
}
