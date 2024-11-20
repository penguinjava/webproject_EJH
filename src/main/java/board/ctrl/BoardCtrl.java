package board.ctrl;

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
public class BoardCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		
		try {
				Map<String, Object> map = new HashMap<String, Object>();
				
				String searchField = req.getParameter("searchField");
				String searchWord = req.getParameter("searchWord");
				if(searchWord != null) {
					map.put("searchField", searchField);
					map.put("searchWord", searchWord);
				}
				int totalCount = dao.selectCount(map);
				
				List<BoardDTO> boardLists = dao.selectList(map);
				dao.close();
				
				map.put("totalCount", totalCount);
				
				
				//날짜 구분
				LocalDate today = LocalDate.now();
				System.out.println("오늘의 날짜 : "+today.toString());
				
				for (BoardDTO list : boardLists) {
					String[] postdate = list.getPostdate().split(" ");
					System.out.println("날짜="+postdate[0]+"/시간"+postdate[1]);
					if(postdate[0].equals(today.toString())) {
						list.setPostdate("오늘 "+postdate[1]);
					}else {
						list.setPostdate(postdate[0]);
					}
				}
				
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
