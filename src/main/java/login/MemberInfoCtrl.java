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

@WebServlet("/memberInfo.do")
public class MemberInfoCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	// 홈에서 로그인이 되어있으면 요청 보내기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//세션의 아이디를 불러오기
		HttpSession session = req.getSession();
		MemberDAO dao = new MemberDAO();
		String id = session.getAttribute("id").toString();
		
		try {
			//세션이 없을시 로그인 화면으로 이동
			if (session.getAttribute("id") == null) {
				JSFunction.alertLocation(resp, "로그인 하세요", "./login.do");
			}else {
				// 정보 불러오기
				MemberDTO dto = dao.selectInfo(id);
				
				String[] date = dto.getJoin_date().split(" ");
				System.out.println(date);
				
				req.setAttribute("join_date", date[0]);
				req.setAttribute("dto", dto);
				req.getRequestDispatcher("./MvcModel2/Test/InfoTest.jsp")
					.forward(req, resp);
			}
		} catch (Exception e) {
			System.out.println("정보 이동중 에러");
			e.printStackTrace();
		}
		
		
		
	}

}
