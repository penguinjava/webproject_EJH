package member.ctrl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/searchPw.do")
public class SearchPwCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("./MvcModel2/Login/SearchPw.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String user_id = req.getParameter("user_id");
		String user_name = req.getParameter("user_name");
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		
		int result = mdao.changePw(user_id, user_name);
		String pw = mdto.getPassword();
		
		
		if(result==1) {
			req.setAttribute("pw", pw);
			JSFunction.alertLocation(resp,"새로운 비밀번호를 발급했습니다.",
					"./MvcModel2/Login/Pw.jsp");
		}
	}
}
