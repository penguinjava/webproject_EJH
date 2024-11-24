package likes;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/like.do")
public class Like extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
		HttpSession session = req.getSession();
		
		String board_id = req.getParameter("board_id"); // 게시물 ID
	    String user_id = session.getAttribute("id").toString();   // 사용자 ID

	    // DAO를 이용해 좋아요 상태 업데이트
	    LikeDAO dao = new LikeDAO();
	    boolean isLiked = dao.likecheck(board_id, user_id); // 좋아요 상태 토글

	    // 응답으로 좋아요 상태(1 또는 0) 반환
	    resp.setContentType("application/json");
	    resp.getWriter().write("{\"like\": " + (isLiked ? 1 : 0) + "}");
	}
}
