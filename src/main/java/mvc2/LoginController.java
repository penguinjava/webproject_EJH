package mvc2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/login.do")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		try {
			String id = req.getParameter("user_id");
			String pw = req.getParameter("password");
			//String nick = req.getParameter("nickname");
			
			dto = dao.login(id, pw);
			dao.close();
			
			if(dto.getUser_id() != null) {
				session.setAttribute("id", id);
				session.setAttribute("nick", dto.getNickname());
				JSFunction.alertLocation(resp, "!로그인 성공!", "index.jsp");
				
				
			}else {
				JSFunction.alertBack(resp,"로그인 실패");
			}
		} catch (Exception e) {
			System.out.println("로그인 오류 발생!");
			e.printStackTrace();
		}
	}
	
	
	
	
}
