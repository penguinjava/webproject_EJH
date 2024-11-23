package file.ctrl;

import java.io.IOException;
import java.time.LocalDate;

import board.BoardDAO;
import board.BoardDTO;
import file.FileDAO;
import file.FileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/fileView.do")
public class FileViewCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		FileDAO fdao = new FileDAO();
		FileDTO fdto = new FileDTO();
		BoardDAO bdao = new BoardDAO();
		BoardDTO bdto = new BoardDTO();
		
		String board_id = req.getParameter("board_id");
		System.out.println("page에서 뷰로 넘어가는 번호 : "+board_id);
		
		bdao.updateVisit(board_id);
		
		//정보 넣기
		bdto = fdao.listView(board_id);
		bdao.close();
		fdao.close();
		
		String[] date = bdto.getPostdate().split(" ");
		System.out.println(date);
		//날짜 구분
		LocalDate today = LocalDate.now();
		System.out.println("오늘의 날짜 : "+today.toString());
		
		if(date[0].equals(today.toString())) {
			bdto.setPostdate("오늘 "+date[1]);
		}else {
			bdto.setPostdate(date[0]);
		}
		
		
		//게시글 내용 줄바꿈처리
		bdto.setContent(bdto.getContent()
					.replaceAll("\r\n", "<br/>"));
		
		System.out.println("게시판 파일 : "+bdto.getOfile());
		System.out.println("게시판 파일 실제이름 : "+bdto.getSfile());
		
		//포워드
		req.setAttribute("today", today);
		req.setAttribute("bdto", bdto);
		req.getRequestDispatcher("./MvcModel2/File/FileView.jsp")
				.forward(req, resp);
		
	}
}
