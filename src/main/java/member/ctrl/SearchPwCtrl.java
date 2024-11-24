package member.ctrl;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.lang3.text.StrTokenizer;

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
		
		try {
			int result = mdao.changePw(user_id, user_name);
			System.out.println("result : "+result);
			mdto = mdao.newPw(user_id, user_name);
			
			System.out.println("newresult : "+mdto.getPassword());
			mdao.close();
			
			String pw = mdto.getPassword();
			System.out.println("발급한 비밀번호 : "+pw);
			
			if(result==1) {
				req.setAttribute("pw", pw);
				req.getRequestDispatcher("./MvcModel2/Login/Pw.jsp")
					.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("비밀번호 이동중 오류");
			e.printStackTrace();
		}
	}
}
