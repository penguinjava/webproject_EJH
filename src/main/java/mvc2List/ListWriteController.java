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

@WebServlet("/listWrite.do")
public class ListWriteController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	
	//페이지 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id") == null) {
			JSFunction.alertLocation(resp, "로그인 하세요",
					"./login.do");
		}else {
			req.getRequestDispatcher("./MvcModel2/List/ListWrite.jsp")
				.forward(req, resp);
		}	
	}
	
	//post
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
			
			//글쓰기
			int result = dao.insertboard(dto);
			dao.close();
			
			// 성공 or 실패?
			if (result == 1) {  // 글쓰기 성공
				resp.sendRedirect("./boardlist.do");
			}
			else {  // 글쓰기 실패
				JSFunction.alertLocation(resp, "글쓰기에 실패했습니다.",
						"/listWrite.do");
			}
		}catch (Exception e) {
			System.out.println("글쓰기 오류");
			e.printStackTrace();
		}
	}
}
