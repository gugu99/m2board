package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	
	private IMemberService memberService;
	
	// 로그인 폼으로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMember") != null) { // 로그인된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view//login.jsp");
		rd.forward(request, response);
	}

	// login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") != null) { // 로그인된 상태
			response.sendRedirect(request.getContextPath() + "/index");
			return;
		}
		// 인코딩
		request.setCharacterEncoding("utf-8");
		// 파라미터
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member paramMember = new Member();
		paramMember.setId(id);
		paramMember.setPw(pw);
		
		memberService = new MemberService();
		
		Member member = memberService.getMemberByLogin(paramMember);
		
		if (member == null) {
			System.out.println("로그인 실패!");
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath() + "/index");
	}

}
