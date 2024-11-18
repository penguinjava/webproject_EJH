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

@WebServlet("/create.do")
public class CreateController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String id = req.getParameter("user_id");
		String nick = req.getParameter("nickname");
		
		//아이디&닉네임 중복 확인
		if(dao.checkId(id)) {
			utils.JSFunction.alertBack(resp, "사용 불가능한 아이디");
			req.setAttribute("user_id", id);
			req.getRequestDispatcher("../MvcModel2/Login/LoginHome.jsp")
				.forward(req, resp);
		}
		
		
		//전화번호 합치기
		String f = req.getParameter("f_number");
		String s = req.getParameter("s_number");
		String number = "010-"+f+"-"+s;
		
		dto.setUser_id(id);
		dto.setUser_name(req.getParameter("user_name"));
		dto.setPassword(req.getParameter("password"));
		dto.setNickname(nick);
		dto.setPhone_number(number);
		dto.setGender(req.getParameter("gender"));
		dto.setAddress(req.getParameter("address"));
		
		String email = req.getParameter("email");
		// DB의 디폰트값 유지를 위해서 입력이 없으면 받지않음
		if (email == null|| email.isEmpty()) {
			dto.setEmail("등록된 이메일이 없습니다");
		}

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
