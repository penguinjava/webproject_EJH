package login;

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
public class LoginCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//loginCheck를 통해서 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			//포워드
			req.getRequestDispatcher("./MvcModel2/Login/LoginHome.jsp")
					.forward(req, resp);
		}catch(Exception e) {
			
			System.out.println("이동중 오류 발생");
			e.printStackTrace();
		}
	}
	
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
			
			String[] date = dto.getJoin_date().split(" ");
			
			req.setAttribute("join_date", date[0]);
			req.setAttribute("dto", dto);
			
			if(dto.getUser_id() != null) {
				session.setAttribute("id", id);
				session.setAttribute("nick", dto.getNickname());
				JSFunction.alert(resp, "!로그인 성공!");
				req.getRequestDispatcher("./home.do")
					.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("로그인 오류 발생!");
			JSFunction.alertBack(resp,"로그인 실패");
			e.printStackTrace();
		}
	}
	
	
	
	
}
