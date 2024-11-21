package member.ctrl;

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

@WebServlet("/userEdit.do")
public class UserEditCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String id = session.getAttribute("id").toString();
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = new MemberDAO();
		
		// 수정할 정보를 얻어온다.
		dto = dao.selectInfo(id);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("./MvcModel2/Login/UserEdit.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			//수정할 내용 가져오기
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			//전화번호 합치기
			String f = req.getParameter("f_number");
			String s = req.getParameter("s_number");
			String number = "010-"+f+"-"+s;
			String user_id = session.getAttribute("id").toString();
			
			System.out.println(email);
			System.out.println(address);
			System.out.println(number);
			System.out.println(user_id);
			
			//수정 내용 적용
			MemberDTO dto = new MemberDTO();
			dto.setEmail(email);
			dto.setPhone_number(number);
			dto.setAddress(address);
			dto.setUser_id(user_id);
			
			//DB에 반영
			MemberDAO dao = new MemberDAO();
			int result = dao.updateInfo(dto);
			
			// 성공여부
			if(result == 1) {
				req.getRequestDispatcher("./memberInfo.do")
					.forward(req, resp);
			}else {
				JSFunction.alertBack(resp, "개인 정보 실패");
			}
			
		} catch (Exception e) {
			System.out.println("수정 실패");
			JSFunction.alert(resp, "개인정보 수정 실패");
			e.printStackTrace();
		}
	}
}
