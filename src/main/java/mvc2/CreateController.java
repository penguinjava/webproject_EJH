package mvc2;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/mvc2/create.do")
public class CreateController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		
		dto.setUser_id(req.getParameter("user_id"));
		dto.setUser_name(req.getParameter("user_name"));
		dto.setPassword(req.getParameter("password"));
		dto.setNickname(req.getParameter("nickname"));
		dto.setPhone_number(req.getParameter("phone_number"));
		dto.setGender(req.getParameter("gender"));
		dto.setAddress(req.getParameter("address"));
		
		String email = req.getParameter("email");
		String phone = req.getParameter("phone_number");
		// DB의 디폰트값 유지를 위해서 입력이 없으면 받지않음
		if (email == null|| email.isEmpty()) {
			dto.setEmail("등록된 이메일이 없습니다");
		}
		
		MemberDAO dao = new MemberDAO();
		int result = dao.insertMember(dto);
		dao.close();
		
		// 회원가입 성공 여부
		if (result == 1) {
			resp.sendRedirect("../index.jsp");
			System.out.println("회원가입 성공");
		}else {
			System.out.println("실패");
		}
	}
}
