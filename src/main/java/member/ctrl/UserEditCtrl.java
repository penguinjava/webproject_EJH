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
		dao.close();
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("./MvcModel2/Login/UserEdit.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			HttpSession session = req.getSession();
			//기존내용 가져오기
			String beforeP = req.getParameter("beforepw");
			String beforeN = req.getParameter("beforephone");
			String beforeA = req.getParameter("beforeaddress");
			
			System.out.println("beforeN : "+ beforeN);

			//수정할 내용 가져오기
			String email = req.getParameter("email");
			String address = req.getParameter("address");
			String password = req.getParameter("password");
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
			if(email != "") {
				dto.setEmail(email);		
			}else {
				dto.setEmail("등록된 이메일이 없음");
			}
			
			System.out.println("이메일 : "+dto.getEmail());
			
			if(address != "") {
				dto.setAddress(address);				
			}else {
				dto.setEmail(beforeA);
			}
			
			System.out.println("주소 : "+dto.getAddress());
			
			if(password != "") {
				dto.setPassword(password);				
			}else {
				dto.setPassword(beforeP);
			}
			
			System.out.println("비밀번호 : "+dto.getPassword());
			
			if(f != "" && s != "") {
				dto.setPhone_number(number);				
			}else {
				dto.setPhone_number(beforeN);
			}
			
			System.out.println("전화번호 : "+dto.getPhone_number());
			
			dto.setUser_id(user_id);
			
			//DB에 반영
			MemberDAO dao = new MemberDAO();
			int result = dao.updateInfo(dto);
			System.out.println("정보 수정 성공!: "+ result);
			dao.close();
			
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
