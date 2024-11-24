package likes;

import common.DBConnPool;

public class LikeDAO extends DBConnPool{
	
	public boolean likecheck(String board_id,String user_id) {
		boolean islike = false;
        // 해당 사용자가 해당 게시물에 대해 좋아요를 눌렀는지 확인
        String query1 = "SELECT likes "
        		+ " FROM likes "
        		+ " WHERE board_id = ? AND user_id = ? ";
        try {
        	psmt = con.prepareStatement(query1);
        	psmt.setString(1, board_id);
        	psmt.setString(2, user_id);
        	rs = psmt.executeQuery();
        	
        	if (rs.next()) {
        		// 이미 좋아요를 눌렀다면, 좋아요 취소 (like = 0)
        		int backLike = rs.getInt("likes");
        		if (backLike == 1) {
        			// 좋아요 취소
        			String query2 = "UPDATE likes "
        					+ " SET likes = 0 "
        					+ " WHERE board_id = ? AND user_id = ? ";
        			psmt = con.prepareStatement(query2);
        			psmt.setString(1, board_id);
        			psmt.setString(2, user_id);
        			psmt.executeUpdate();
        			islike = false;
        		} else {
        			// 이미 취소 상태라면 아무 동작 안 함
        			islike  = false;
        		}
        	} else {
        		// 좋아요를 처음 눌렀다면, 새로 추가 (like = 1)
        		String query3 = " INSERT INTO likes "
        				+ " (board_id, user_id, likes) "
        				+ " VALUES (?, ?, 1) ";
        		psmt = con.prepareStatement(query3);
        		psmt.setString(1, board_id);
        		psmt.setString(2, user_id);
        		psmt.executeUpdate();
        		islike = true;
        	}
		} catch (Exception e) {
			System.out.println("좋아요 처리중 오류");
			e.printStackTrace();
		}
        return islike;
	}
}
