package member.ctrl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import member.MemberDAO;
import member.MemberDTO;
import utils.JSFunction;

@WebServlet("/create.do")
public class CreateCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	// 페이지 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			req.getRequestDispatcher("./MvcModel2/Login/CreateHome.jsp")
				.forward(req, resp);
			
			
		}catch(Exception e) {
			System.out.println("이동중 오류 발생");
			e.printStackTrace();
		}
	
	}
	
	// 실제 작성
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		String id = req.getParameter("user_id");
		String nick = req.getParameter("nickname");
		
		
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
			dto.setEmail("등록된 이메일이 없음");
		}

		int result = dao.insertMember(dto);
		dao.close();
		
		// 회원가입 성공 여부
		if (result == 1) {
			System.out.println("회원가입 성공");
			req.getRequestDispatcher("index.jsp")
			.forward(req, resp);
		}else {
			System.out.println("실패");
		}
	}
}
