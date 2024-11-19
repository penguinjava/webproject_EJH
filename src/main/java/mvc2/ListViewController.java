package mvc2;

import java.io.IOException;
import java.time.LocalDate;

import board.BoardDAO;
import board.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listView.do")
public class ListViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 자유게시판 게시글 내용 뷰
		BoardDAO dao = new BoardDAO();
		//게시판을 식별함
		String board_id = req.getParameter("board_id");
		//조회수 증가
		dao.updateVisit(board_id);
		
		BoardDTO dto = dao.listView(board_id);
		//DB반납
		dao.close();
		
		String[] date = dto.getPostdate().split(" ");
		
		req.setAttribute("ymd", date[0]);
		req.setAttribute("time", date[1]);
		req.setAttribute("dto", dto);
		
		LocalDate today = LocalDate.now();
		
		//게시글 내용 줄바꿈처리
		dto.setContent(dto.getContent()
					.replaceAll("\r\n", "<br/>"));
		
		//포워드
		req.setAttribute("today", today);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("./MvcModel2/List/ListView.jsp")
				.forward(req, resp);
	}
}
