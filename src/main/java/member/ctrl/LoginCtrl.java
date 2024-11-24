package member.ctrl;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			// 쿠키 확인
			Cookie[] cookies = req.getCookies();
			String user_id = null;
			
			System.out.println("쿠키 내용 확인 : "+ cookies.toString());
			// 값이 있으면 확인
			if (cookies !=null) {
				for(Cookie c : cookies) {
					// 쿠키 반복확인해서 이름이 user_id가 있으면 값불러오기
					if("idCookie".equals(c.getName())) {
						// 사용자의 아이디 가져오기
						user_id = c.getValue();
						System.out.println("확인 된 쿠키 값 : "+user_id);
						break;
					}
				}
			}
			//아이디 저장
			req.setAttribute("cookieId", user_id);

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
			// 아이디 체크 확인
			String rememberId = req.getParameter("rememberId");
			
			System.out.println(rememberId);
			
			dto = dao.login(id, pw);
			dao.close();
			
			String[] date = dto.getJoin_date().split(" ");
			
			req.setAttribute("join_date", date[0]);
			req.setAttribute("dto", dto);
			
			if(dto.getUser_id() != null) {
				
				if(rememberId != null) {
					// 로그인하고 체크값이 on이면 쿠키 생성
					if(rememberId.equals("on")) {
						Cookie cookie = new Cookie("idCookie", id);
						//쿠키 속성 설정
						cookie.setPath("/");
						// 1주일 유지
						cookie.setMaxAge(60*60*24*7);
						
						//브라우저에 저장
						resp.addCookie(cookie);
						
						//디버깅
						System.out.println("쿠키 생성됨");
						System.out.println("쿠키 이름: " + cookie.getName());
						System.out.println("쿠키 값: " + cookie.getValue());
					}else {
						// 쿠키삭제
						Cookie deleteCookie = new Cookie("user_id", "");
						deleteCookie.setMaxAge(0); // 즉시 삭제
						deleteCookie.setPath("/");
						resp.addCookie(deleteCookie);
						
						//디버깅
						System.out.println("쿠키 삭제 요청됨");
						System.out.println("쿠키 이름: " + deleteCookie.getName());
						System.out.println("쿠키 값: " + deleteCookie.getValue());
					}
				}
				
				
				session.setAttribute("id", id);
				session.setAttribute("nick", dto.getNickname());
				JSFunction.alert(resp, "!로그인 성공!");
				
				//로그인을 위치 지정
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
