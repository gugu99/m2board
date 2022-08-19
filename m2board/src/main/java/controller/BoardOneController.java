package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/boardOne")
public class BoardOneController extends HttpServlet {
	
	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		
		System.out.println("session --- " + session.getAttribute("loginMember"));
		
		if (session.getAttribute("loginMember") == null) { // 로그인안된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		boardService = new BoardService();
		
		Map<String, Object> map = boardService.getBoardOne(boardNo);
		
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
	}

}
