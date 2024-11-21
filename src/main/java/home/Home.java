package home;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home.do")
public class Home extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	// 메인 화면으로
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			req.getRequestDispatcher("index.jsp")
				.forward(req, resp);
			
		} catch (Exception e) {
			System.err.println("index 연결 실패");
			e.printStackTrace();
		}
	}
}
