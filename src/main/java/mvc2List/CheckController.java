package mvc2List;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;

@WebServlet("/check.do")
public class CheckController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
        String value = req.getParameter("value");
        MemberDAO dao = new MemberDAO();

        boolean available = dao.check(type, value); // 데이터베이스에서 확인
        
        // 결과를 JSON 형식으로 응답
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.write("{ \"available\": " + available + " }");
        out.flush();
	}
}
