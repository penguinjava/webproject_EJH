package mvc2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.BoardDAO;
import board.BoardDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/boardlist.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String search = req.getParameter("search");
		if(search != null) {
			map.put("search", search);
		}
		int totalCount = dao.selectCount(map);
		
		List<BoardDTO> boardLists = dao.selectList(map);
		dao.close();
		
		map.put("totalCount", totalCount);
		
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/MvcModel2/List/ListHome.jsp")
		.forward(req, resp);
	}
}
