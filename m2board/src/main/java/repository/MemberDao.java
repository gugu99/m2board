package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class MemberDao implements IMemberDao{
	
	// 회원가입
	@Override
	public int insertMember(Connection conn, Member paramMember) throws SQLException {
		System.out.println("--------------------MemberDao.insertMember()");
		
		int result = 0;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO member (id, pw) VALUES (?, PASSWORD(?))";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			
			System.out.println("stmt --- " + stmt);
			
			result = stmt.executeUpdate();
			
			System.out.println("result --- " + result);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return result;
	}

	// 로그인
	@Override
	public Member selectMemberByLogin(Connection conn, Member paramMember) throws SQLException {
		System.out.println("--------------------MemberDao.selectMemberByLogin()");
		
		Member member = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT id FROM member WHERE id = ? AND pw = PASSWORD(?)";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			
			System.out.println("stmt --- " + stmt);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
			}
			
			System.out.println("member --- " + member);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return member;
	}
	
	
}
