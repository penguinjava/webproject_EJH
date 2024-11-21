package cookie;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

public class CookieSave{
	
	
	public static void idCookie(HttpServletResponse resp, String user_id) {
		
		Cookie idCookie = new Cookie("user_id", user_id);
	
		//쿠키 속성 설정
		idCookie.setPath("/");
		// 1주일 유지
		idCookie.setMaxAge(60*60*24*7);
		
		//응답
		resp.addCookie(idCookie);
	}
}
