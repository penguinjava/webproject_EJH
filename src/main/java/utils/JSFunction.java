package utils;

import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

public class JSFunction {
	
	//경고창 -> 다른 페이지로
	public static void alertLocation(HttpServletResponse resp,
						String msg, String url) {
		
		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script =""
						+ "<script>"
						+ "		alert('" + msg + "');"
						+ " 	location.href='" + url +"';"
						+ "</script>";
			writer.print(script);
		} catch (Exception e) {}
	}
	
	
	// 경고창 -> 이전페이지
	public static void alertBack(HttpServletResponse resp,
			String msg) {

		try {
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script =""
					+ "<script>"
					+ "		alert('" + msg + "');"
					+ " 	history.back();"
					+ "</script>";
			writer.print(script);
		} catch (Exception e) {}
	}
}
