package file.ctrl;

import java.io.IOException;

import board.BoardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/fileDelete.do")
public class FileDeleteCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		HttpSession session = req.getSession();
		if(session.getAttribute("id")==null) {
			JSFunction.alertLocation(resp, "로그인 해주세요", 
					"./login.do");
		return;
		}
		
		//게시물 가져오기
		String board_id  = req.getParameter("board_id");
		BoardDAO bdao = new BoardDAO();
		bdao.listView(board_id);
		
		//작성자 el에서 확인
		
		//외래키로 cascade로 관련테이블 삭제
		int bresult = bdao.deleteList(board_id);
		bdao.close();
		
		if(bresult==1) {
			//삭제 알림
			JSFunction.alertLocation(resp, "삭제 했습니다.",
					"./filePage.do");
		}else {
			JSFunction.alertBack(resp, "삭제 실패");
		}
	}
}
