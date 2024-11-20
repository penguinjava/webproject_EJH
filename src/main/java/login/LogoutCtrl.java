package login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

@WebServlet("/logout.do")
public class LogoutCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String id = (String) session.getAttribute("id");
		
		try {
			if(id != null) {
				session.removeAttribute("id");
				session.removeAttribute("nick");
				
				session.invalidate();
				
				JSFunction.alertLocation(resp, "!로그아웃 성공!", "home.do");
			
			}
		} catch (Exception e) {
			System.out.println("로그아웃 에러발생");
			e.printStackTrace();
		}
	}
}