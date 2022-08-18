package service;

import vo.Member;

public interface IMemberService {
	
	// 로그인
	Member getMemberByLogin(Member paramMember);
	// 회원가입
	boolean addMember(Member paramMember);
}
