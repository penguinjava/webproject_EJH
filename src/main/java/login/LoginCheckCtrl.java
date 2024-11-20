package login;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/loginCheck.do")
public class LoginCheckCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//로그인 이동시 체크
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		try {
				//세션이 없을시 로그인 화면으로 이동
				if (session.getAttribute("id") == null) {
				JSFunction.alertLocation(resp, "로그인 하세요", "./login.do");
			}else {
				//로그인이 되어있으면
				resp.sendRedirect("./memberInfo.do");
			}
				
		}catch(Exception e) {
			System.out.println("이동중 오류 발생");
			e.printStackTrace();
		}
	}
}
