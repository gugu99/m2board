package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;


@WebServlet("/signUp")
public class SignUpController extends HttpServlet {
	
	private IMemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/index");
		}
		
		request.getRequestDispatcher("/WEB-INF/view/signUp.jsp").forward(request, response);
	}

	
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
		String addr = request.getParameter("addr"); // 우편번호 주소
		String detailAddress = request.getParameter("detailAddress"); // 상세주소
		String address = addr + " " + detailAddress; // 전체 주소
		
		Member paramMember = new Member();
		paramMember.setId(id);
		paramMember.setPw(pw);
		paramMember.setAddress(address);
		
		memberService = new MemberService();
		boolean add = memberService.addMember(paramMember);
		
		if (!add) {
			System.out.println("회원가입 실패!");
			response.sendRedirect(request.getContextPath() + "/signUp");
			return;
		}
		
		System.out.println("회원가입 성공!");
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
