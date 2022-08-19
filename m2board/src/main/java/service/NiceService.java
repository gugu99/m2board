package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import repository.INiceDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService{
	
	private INiceDao niceDao;
	private DBUtil dbUtil;

	// 좋아요 올리기
	@Override
	public boolean addNice(Nice paramNice) {
		Connection conn = null;
		dbUtil = new DBUtil();
		int result = -999;
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false); // 자동 커밋을 막아준다.
			
			niceDao = new NiceDao();
			if (niceDao.insertNice(conn, paramNice) != 1) { // 좋아요 실패하면
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

	// 좋아요 가져오기
	@Override
	public int getNiceCount(int boardNo) {
		int niceCnt = 0;
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			
			niceDao = new NiceDao();
			niceCnt = niceDao.selectNiceCount(conn, boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return niceCnt;
	}

	
}
