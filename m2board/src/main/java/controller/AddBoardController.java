package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;
import vo.Member;

@WebServlet("/addBoard")
public class AddBoardController extends HttpServlet {
	
	private IBoardService boardService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addBoardForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMember") == null) { // 로그인안된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 세션 값 가져오기
		String id = ((Member)session.getAttribute("loginMember")).getId();
		
		Board board = new Board();
		board.setTitle(title);
		board.setId(id);
		board.setContent(content);
		
		boardService = new BoardService();
		if (boardService.addBoard(board)) {
			System.out.println("글쓰기 성공!");
		}
		response.sendRedirect(request.getContextPath()+ "/boardList");
	}

}
