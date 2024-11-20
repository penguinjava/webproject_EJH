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

@WebServlet("/listEdit.do")
public class ListEditController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// 세션에서 아디이 확인
		//로그인을 해도 시간이 나나면 끊길 수 있음 
		if(session.getAttribute("id")==null) {
			JSFunction.alertLocation(resp,"다시 로그인 해주세요.",
						"./login.do");
			return;
		}
		
		// 수정할 게시물을 얻어온다.
		String board_id	= req.getParameter("board_id");
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.listView(board_id);
		
		//본인확인은 JSP에서 EL로 처리
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("../MvcModel2/List/ListEdit.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		// 세션에서 아디이 확인
		//로그인을 해도 시간이 나나면 끊길 수 있음;
		if(session.getAttribute("id")==null) {
			JSFunction.alertLocation(resp,"다시 로그인 해주세요.",
						"./login.do");
			return;
		}
		
		// 수정할 내용 가져오기
		String board_id = req.getParameter("board_id");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		// 수정 내용 적용
		BoardDTO dto = new BoardDTO();
		dto.setBoard_id(board_id);
		dto.setUser_id(session.getAttribute("id").toString());
		dto.setTitle(title);
		dto.setContent(content);
		
		// 수정내용 DB에 반영
		BoardDAO dao = new BoardDAO();
		int result = dao.updateList(dto);
		dao.close();
		
		// 성공시 해당 해당 페이지로
		if (result == 1) {
			resp.sendRedirect("./listView.do?board_id="+board_id);
		}else {
			// 수정 적용 안된 board_id로 이동
			JSFunction.alertLocation(resp, "세션 끊임",
						"./listView.do?board_id="+board_id);
		}
	}
}
