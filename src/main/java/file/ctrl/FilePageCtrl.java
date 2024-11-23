package file.ctrl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.BoardDTO;
import file.FileDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

@WebServlet("/filePage.do")
public class FilePageCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FileDAO fdao = new FileDAO();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String searchField = req.getParameter("searchField");
			String searchWord = req.getParameter("searchWord");
			
			if(searchWord != null) {
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}
			int totalCount = fdao.selectCount(map);
			
			System.out.println("자료실 게시판 갯수 : "+totalCount);
			
			
			//페이징 처리//
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(
					application.getInitParameter("POSTS_PER_PAGE"));
			
			System.out.println(pageSize);
			
			int blockPage = Integer.parseInt(
					application.getInitParameter("PAGES_PER_BLOCK"));
			
			System.out.println(blockPage);
			
			//현재 페이지 확인
			int pageNum = 1;
			String pageTemp = req.getParameter("pageNum");
			if(pageTemp != null && !pageTemp.equals("")) {
				pageNum = Integer.parseInt(pageTemp);			
			}
			
			//목록에 출력할 게시물 범위 계산
			int start = (pageNum -1) * pageSize + 1;
			int end = pageNum * pageSize;
			map.put("start", start);
			map.put("end", end);
			//페이지 끝//
			
			
			List<BoardDTO> fileLists = fdao.boardPage(map);
			fdao.close();
			
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
					blockPage, pageNum,
					"./filePage.do");
			
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			System.out.println(pagingImg);
			System.out.println(totalCount);
			System.out.println(pageSize);
			System.out.println(pageNum);
			
			//날짜 구분
			LocalDate today = LocalDate.now();
			System.out.println("오늘의 날짜 : "+today.toString());
			
			for (BoardDTO list : fileLists) {
				String[] postdate = list.getPostdate().split(" ");
				System.out.println("날짜="+postdate[0]+"/시간"+postdate[1]);
				if(postdate[0].equals(today.toString())) {
					list.setPostdate("오늘 "+postdate[1]);
				}else {
					list.setPostdate(postdate[0]);
				}
			}
			
			
			req.setAttribute("fileLists", fileLists);
			req.setAttribute("map", map);
			req.getRequestDispatcher("./MvcModel2/File/FileListHome.jsp")
			.forward(req, resp);
			
		} catch (Exception e) {
			System.out.println("자료실 게이판 이동중 오류");
			e.printStackTrace();
		}
	}
}
