package comment.ctrl;

import java.io.IOException;

import comment.CommentDAO;
import comment.CommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/comment.do")
public class CommentCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//중간에 세션 끊임 방지
		HttpSession session = req.getSession();
		if(session.getAttribute("id") == null) {
			JSFunction.alertLocation(resp, "로그인 하세요",
					"./login.do");
			return;
		}
			
		//댓글 내용 적용
		String board_id = req.getParameter("board_id");
		String user_id = session.getAttribute("id").toString();
		String cm = req.getParameter("cm");
		
		System.out.println("댓글을 작성할때 넘어가는 번호 : "+board_id);
		try {
			CommentDTO cdto = new CommentDTO();
			CommentDAO cdao = new CommentDAO();
			
			//dto에 적용
			cdto.setBoard_id(board_id);
			cdto.setUser_id(user_id);
			cdto.setContent(cm);
			cdto.setNickname(session.getAttribute("nick").toString());
			
			int result = cdao.cm(cdto);
			cdao.close();
			
			req.setAttribute("cdto", cdto);
			
			if(result == 1) {
				req.getRequestDispatcher("./commentView.do")
					.forward(req, resp);
			}else {  // 글쓰기 실패
				JSFunction.alertLocation(resp, "댓글 실패",
						"./comment.do");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
