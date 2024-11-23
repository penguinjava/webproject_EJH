package file.ctrl;

import java.io.IOException;

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

@WebServlet("/fileEdit.do")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 100
)
public class FileEditCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		try {
			if(session.getAttribute("id") == null) {
				JSFunction.alertLocation(resp, "로그인 해주세요",
						"./login.do");
				return;
			}
			
			//게시물 얻어오기
			String board_id = req.getParameter("baord_id");
			FileDAO fdao = new FileDAO();
			BoardDTO bdto = fdao.listView(board_id);
			
			//작성자 확인 el로 처리
			
			req.setAttribute("bdto", bdto);
			req.getRequestDispatcher("./MvcModel2/File/FileEdit.jsp")
				.forward(req, resp);
			
		} catch (Exception e) {
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		try {
			if(session.getAttribute("id") == null) {
				JSFunction.alertLocation(resp, "로그인 해주세요",
						"./login.do");
				return;
			}
			
			//본인확인 el로 처리
			
			//파일 업로드
			String saveDirectory = req.getServletContext()
					.getRealPath("/Uploads");
			
			String originalFileName = "";
			try {
				originalFileName = FileUtil
						.uploadFile(req, saveDirectory);
				
				System.out.println("수정할 파일 : "+originalFileName);
			} catch (Exception e) {
				e.printStackTrace();
				JSFunction.alertBack(resp, "파일 업로드 오류");
				return;
			}
			
			String board_id = req.getParameter("board_id");
			String prevOfile = req.getParameter("prevOfile");
			String prevSfile = req.getParameter("prevSfile");
			
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			
			//dto에 저장
			BoardDTO bdto = new BoardDTO();
			bdto.setBoard_id(board_id);
			bdto.setUser_id(session.getAttribute("id").toString());
			bdto.setTitle(title);
			bdto.setContent(content);
			
			//파일 설정
			if(originalFileName != "") {
				String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
				
				bdto.setOfile(originalFileName);
				bdto.setSfile(savedFileName);
				
				//기존파일 삭제
				FileUtil.deleteFile(req, "/Uploads", prevSfile);				
			}else {
				bdto.setOfile(prevOfile);
				bdto.setSfile(prevSfile);
			}
			
			BoardDAO bdao = new BoardDAO();
			FileDAO fdao = new FileDAO();
			FileDTO fdto = new FileDTO();
			int bresult = bdao.updateList(bdto);
			int fresult = fdao.updateFile(fdto);
			
			if(bresult ==1 || fresult ==1) {
				resp.sendRedirect("./fileView.do?board_id="+board_id);
			}else {
				JSFunction.alertBack(resp, "수정 실패");
			}
			
		} catch (Exception e) {
			
		}
	}
}
