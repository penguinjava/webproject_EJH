package file.ctrl;

import java.io.IOException;

import file.FileDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.FileUtil;

@WebServlet("/download.do")
public class DownloadCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String ofile = req.getParameter("ofile");
		String sfile = req.getParameter("sfile");
		String board_id = req.getParameter("board_id");
		System.out.println("뷰에서 다운로드로 넘어온 번호 : "+ board_id);
		
		FileUtil.download(req, resp, "/Uploads", sfile, ofile);
		
		FileDAO fdao = new FileDAO();
		
		fdao.downCount(board_id);
		fdao.close();
	}
}
