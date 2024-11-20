package mvc2List;

import java.io.IOException;

import board.BoardDAO;
import board.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("listDelete.do")
public class ListDeleteController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp, "로그인 해주세요", 
					"./login.do");
		return;
		}
		
		//게시물 얻어오기
		String board_id = req.getParameter("board_id");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.listView(board_id);
		
		// 작성자 확인은 EL로 처리
		int result = dao.deleteList(board_id);
		dao.close();
		
		//삭제 알림
		JSFunction.alertLocation(resp, "삭제 했습니다.",
				"./listView.do");
		
	}
}
