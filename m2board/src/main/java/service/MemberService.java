package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import repository.IMemberDao;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService{
	
	private IMemberDao memberDao;
	private DBUtil dbUtil;

	@Override
	public Member getMemberByLogin(Member paramMember) {
		Member member = null;
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			 memberDao = new MemberDao();
			 member = memberDao.selectMemberByLogin(conn, paramMember);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return member;
	}

	@Override
	public boolean addMember(Member paramMember) {
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false); // 자동 커밋을 막아준다
			
			memberDao = new MemberDao();
			if (memberDao.insertMember(conn, paramMember) != 1) { // 회원가입 실패시
				throw new Exception(); // 강제 예외 발생
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}

	
}
