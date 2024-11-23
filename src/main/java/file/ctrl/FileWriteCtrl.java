package file.ctrl;

import java.io.IOException;
import java.util.ArrayList;

import board.BoardDAO;
import board.BoardDTO;
import file.FileDAO;
import file.FileDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.FileUtil;
import utils.JSFunction;

@WebServlet("/fileWrite.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 100
)
public class FileWriteCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("id") == null) {
			JSFunction.alertLocation(resp, "로그인 하세요",
					"./login.do");
		}else {
			req.getRequestDispatcher("./MvcModel2/File/FileWrite.jsp")
				.forward(req, resp);
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			HttpSession session = req.getSession();
			if(session.getAttribute("id")==null) {
				JSFunction.alert(resp, "로그인 하세요");
				req.getRequestDispatcher("./login.do")
					.forward(req, resp);
				return;
			}
		
		
		try {
			String saveDirectory = getServletContext()
					.getRealPath("/Uploads");
			
//			System.out.println(saveDirectory);
			
			String originalFileName = "";
			try {
				originalFileName = FileUtil
						.uploadFile(req, saveDirectory);
				
			} catch (Exception e) {
				JSFunction.alertBack(resp, "파일업로드 오류");
				
				return;
			}
			
			//board 와 file DTO / DAO 가져오기
			BoardDTO bdto = new BoardDTO();
			BoardDAO bdao = new BoardDAO();
			FileDTO fdto = new FileDTO();
			FileDAO fdao = new FileDAO();
			
			bdto.setUser_id(session.getAttribute("id").toString());
			bdto.setTitle(req.getParameter("title"));
			bdto.setContent(req.getParameter("content"));
			bdto.setCategory(req.getParameter("category"));
			
			//게시판 받기
			int bresult = fdao.insertboard(bdto,fdto);
			System.out.println(bresult);
			String board_id = fdto.getBoard_id();
			
			System.out.println("게시판 고유번호 : " + board_id);
			
				
			if(originalFileName != "") {
				String savedFileName = FileUtil
						.renameFile(saveDirectory, originalFileName);	
				fdto.setOfile(originalFileName);
				fdto.setSfile(savedFileName);
				}
			
			int fresult = fdao.saveFile(fdto);
			System.out.println("파일테이블에 저장 :" + fresult);
			bdao.close();
			fdao.close();
			
			if(bresult == 1|| fresult == 1) {
				resp.sendRedirect("./filePage.do");
			}else{
				JSFunction.alert(resp, "게시글 작성 실패");
				resp.sendRedirect("./fileWrite.do");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("./filePage.do")
				.forward(req, resp);
		}
	}
}
