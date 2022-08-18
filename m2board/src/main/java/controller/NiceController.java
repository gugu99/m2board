package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.INiceService;
import service.NiceService;
import vo.Member;
import vo.Nice;

@WebServlet("/nice")
public class NiceController extends HttpServlet {

	private INiceService niceService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("1.-------------niceController");
		HttpSession session = request.getSession();
		
		System.out.println("session --- " + session.getAttribute("loginMember"));
		
		if (session.getAttribute("loginMember") == null) { // 로그인안된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		// 파라미터
		String id = ((Member)session.getAttribute("loginMember")).getId();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		System.out.println("2.----------");
		
		Nice paramNice = new Nice();
		paramNice.setBoardNo(boardNo);
		paramNice.setId(id);
		
		niceService = new NiceService();
		boolean add = niceService.addNice(paramNice);
		
		System.out.println("3.-----------add --- " + add);
		
		if (!add) {
			System.out.println("좋아요 실패!");
			return;
		}
		
		System.out.println("좋아요 성공!");
		
		int niceCnt = niceService.getNiceCount(boardNo);
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(""+niceCnt);
		
		System.out.println("jsonStr --- " + jsonStr);
		
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
		
		out.flush();
		out.close(); // 스트림은 가비지 콜렉터 대상이 아니기때문에 close해준다.
		
	}

}
