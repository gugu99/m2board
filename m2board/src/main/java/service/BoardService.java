package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService{
	
	private IBoardDao boardDao;
	private DBUtil dbUtil;
	
	
	
	// 게시판 글 상세보기
	@Override
	public Map<String, Object> getBoardOne(int boardNo) {
		
		Map<String, Object> map = null;
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			
			boardDao = new BoardDao();
			
			map = boardDao.selectBoardOne(conn, boardNo);
			
			if (map != null) {
				boardDao.updateRead(conn, boardNo);
			}
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
		
		return map;
	}

	// 게시판 글 쓰기
	@Override
	public boolean addBoard(Board paramBoard) {
		
		Connection conn = null;
		dbUtil = new DBUtil();
		
		try {
			conn = dbUtil.getConnection();
			conn.setAutoCommit(false); // 자동 커밋을 막는다.
			boardDao = new BoardDao();
			
			if (boardDao.insertBoard(conn, paramBoard) != 1) { // 입력 실패하면
				throw new Exception(); // 강제 오류 발생
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


	// 게시판 글 목록
	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) {
		
		Connection conn = null;
		dbUtil = new DBUtil();
		int totalRow = 0;
		int lastPage = 0;
		
		Map<String, Object> map = new HashMap<>();
		
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			conn = dbUtil.getConnection();
			
			boardDao = new BoardDao();
			map.put("list" , boardDao.selectBoardListByPage(conn, rowPerPage, beginRow));
			totalRow = boardDao.selectBoardCount(conn);
			lastPage = (totalRow % rowPerPage != 0) ? (totalRow / rowPerPage) +1 : totalRow / rowPerPage;
			
			map.put("lastPage", lastPage);
			
			
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
		
		return map;
	}

	
}
