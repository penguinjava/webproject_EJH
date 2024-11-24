package comment.ctrl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.BoardDAO;
import board.BoardDTO;
import comment.CommentDAO;
import comment.CommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/commentView.do")
public class CommentViewCtrl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 자유게시판 게시글 내용 뷰
		BoardDAO bdao = new BoardDAO();
		CommentDAO cdao = new CommentDAO();
		Map<String, Object> map = new HashMap<String,Object>();
		//게시판 식별
		String board_id = req.getParameter("board_id");
		System.out.println("댓글게시판에서 넘어온 게시판번호 : "+board_id);
		
		//댓글에대한 리스트
		int commentCount = cdao.Commentcount(board_id);
		map.put("commentCount", commentCount);
		List<CommentDTO> commentList = cdao.Commentlist(map,board_id);
		
		
		//조회수 증가
		bdao.updateVisit(board_id);
		
		BoardDTO bdto = bdao.listView(board_id);
		CommentDTO cdto = cdao.cmView(board_id);
		//DB반납
		bdao.close();
		cdao.close();
		
		System.out.println("댓글 시간"+cdto.getCm_date());
		
		//댓글 날짜 구분
		LocalDate today = LocalDate.now();
		System.out.println("오늘의 날짜 : "+today.toString());
		
		for (CommentDTO list : commentList) {
			String[] cm_date = list.getCm_date().split(" ");
			System.out.println("날짜="+cm_date[0]+"/시간"+cm_date[1]);
			if(cm_date[0].equals(today.toString())) {
				list.setCm_date("오늘 "+cm_date[1]);
			}else {
				list.setCm_date(cm_date[0]);
			}
		}
		
		
		//게시물 날짜 구분		
		String[] date = bdto.getPostdate().split(" ");
		System.out.println(date);
		if(date[0].equals(today.toString())) {
			bdto.setPostdate("오늘 "+date[1]);
		}else {
			bdto.setPostdate(date[0]);
		}
		
		System.out.println("여기까지는 나옴..");
		
		try {
			if(bdto.getContent()!=null) {
				//게시글 내용 줄바꿈처리
				bdto.setContent(bdto.getContent()
						.replaceAll("\r\n", "<br/>"));				
			}else if(cdto.getContent()!=null){
				cdto.setContent(cdto.getContent()
						.replace("\r\n", "<br/>"));							
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		for(CommentDTO lists : commentList) {
			lists.getNickname();
			System.out.println("뷰로 보내지는 닉네임 : "+lists.getNickname());
		}
		
		//포워드
		req.setAttribute("map", map);
		req.setAttribute("commentList", commentList);
		req.setAttribute("cdto", cdto);
		req.setAttribute("today", today);
		req.setAttribute("bdto", bdto);
		req.getRequestDispatcher("./MvcModel2/Comment/CommentView.jsp")
				.forward(req, resp);
	}
}
