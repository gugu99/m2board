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

@WebServlet("/boardList")
public class BoardListController extends HttpServlet {

	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.println("session --- " + session.getAttribute("loginMember"));
		
		if (session.getAttribute("loginMember") == null) { // 로그인안된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		
		// 컨트롤러
		// 1) 요청 받아 분석
		int rowPerPage = 10;
		
		int currentPage = 1;
		if (request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		// 2) 서비스 레이어를 요청(메서드 호출) -> 모델값(자료구조) 구하기위함
		boardService = new BoardService();
		Map<String, Object> map = boardService.getBoardList(rowPerPage, currentPage);
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("list", map.get("list"));
		
		// 3) 뷰 포워딩
		request.getRequestDispatcher("/WEB-INF/view/boardList.jsp").forward(request, response);
	}

}
