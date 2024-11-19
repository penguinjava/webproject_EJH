package mvc2;

import java.io.IOException;
import java.time.LocalDate;
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
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/boardlist.do")
public class ListController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		try {
				Map<String, Object> map = new HashMap<String, Object>();
				
				String search = req.getParameter("search");
				if(search != null) {
					map.put("search", search);
				}
				int totalCount = dao.selectCount(map);
				
				List<BoardDTO> boardLists = dao.selectList(map);
				dao.close();
				
				map.put("totalCount", totalCount);
				
				//날짜 구분
				String postdate = (String)map.get("postdate");
				
				String[] date = postdate.split(" ");
				
				req.setAttribute("ymd", date[0]);
				req.setAttribute("time", date[1]);
				
				LocalDate today = LocalDate.now();
				req.setAttribute("today", today);
				
				req.setAttribute("boardLists", boardLists);
				req.setAttribute("map", map);
				req.getRequestDispatcher("./MvcModel2/List/ListHome.jsp")
					.forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("게시판 이동중 오류");
			e.printStackTrace();
		}
	}
}
