package comment.ctrl;

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

@WebServlet("/commentWrite.do")
public class CommentWriteCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id") == null) {
			JSFunction.alertLocation(resp, "로그인 하세요",
					"./login.do");
		}else {
			req.getRequestDispatcher("./MvcModel2/Comment/CommentWrite.jsp")
				.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		try {
			// 여기서부터 실행
			BoardDAO dao = new BoardDAO();
			BoardDTO dto = new BoardDTO();
			
			dto.setUser_id(session.getAttribute("id").toString());
			dto.setTitle(req.getParameter("title"));
			dto.setContent(req.getParameter("content"));
			dto.setCategory(req.getParameter("category"));
			
			//글쓰기
			int result = dao.insertboard(dto);
			dao.close();
			
			
			req.setAttribute("dto", dto);
			
			// 성공 or 실패?
			if (result == 1) {  // 글쓰기 성공
				resp.sendRedirect("./commentPage.do");
			}
			else {  // 글쓰기 실패
				JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.",
						"./commentWrite.do");
			}
		}catch (Exception e) {
			System.out.println("글쓰기 오류");
			e.printStackTrace();
		}
	}
}
