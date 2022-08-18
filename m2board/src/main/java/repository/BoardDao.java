package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Board;

public class BoardDao implements IBoardDao{
	
	// 조회수 올리기
	@Override
	public void updateRead(Connection conn, int boardNo) throws SQLException {
		System.out.println("--------------------BoardDao.selectBoardOne()");
		PreparedStatement stmt = null;
		String sql = "UPDATE board SET `read` = `read`+1 WHERE board_no = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			System.out.println("stmt --- " + stmt);
			
			stmt.executeUpdate();
			
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
	}
	
	// 게시판 글 상세보기
	@Override
	public Map<String, Object> selectBoardOne(Connection conn, int boardNo) throws SQLException {
		System.out.println("--------------------BoardDao.selectBoardOne()");
		Map<String, Object> map = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql= "SELECT b.title, b.id, b.content, b.`read`, b.create_date createDate, t.cnt FROM board b LEFT JOIN (SELECT board_no, COUNT(*) cnt FROM nice GROUP BY board_no) t ON b.board_no = t.board_no WHERE b.board_no = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			System.out.println("stmt --- " + stmt);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				map = new HashMap<String, Object>();
				map.put("boardNo", boardNo);
				map.put("title", rs.getString("title"));
				map.put("content", rs.getString("content"));
				map.put("content", rs.getString("content"));
				map.put("read", rs.getInt("read"));
				map.put("createDate", rs.getString("createDate"));
				map.put("cnt", rs.getInt("cnt"));
				
			}
		} finally {
			if (rs != null)  {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		
		return map;
	}

	//  게시판 글쓰기
	@Override
	public int insertBoard(Connection conn, Board paramBoard) throws SQLException {
		System.out.println("--------------------BoardDao.insertBoard()");
		
		int result = 0;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO board (title, id, content, create_date) VALUES (?,?,?,NOW())";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramBoard.getTitle());
			stmt.setString(2, paramBoard.getId());
			stmt.setString(3, paramBoard.getContent());
			
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

	// 게시판 목록
	@Override
	public List<Map<String, Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException {
		System.out.println("--------------------BoardDao.selectBoardListByPage()");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.board_no boardNo, b.title, b.id, b.`read`, b.create_date createDate, t.cnt FROM board b LEFT JOIN (SELECT board_no, COUNT(*) cnt FROM nice GROUP BY board_no) t ON b.board_no = t.board_no ORDER BY create_date DESC LIMIT ?,?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			
			System.out.println("stmt --- " + stmt);
			
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("boardNo",rs.getInt("boardNo"));
				m.put("title",rs.getString("title"));
				m.put("id",rs.getString("id"));
				m.put("read",rs.getInt("read"));
				m.put("createDate",rs.getString("createDate"));
				m.put("cnt",rs.getInt("cnt"));
				
				list.add(m);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}

	@Override
	public int selectBoardCount(Connection conn) throws SQLException {
		System.out.println("--------------------BoardDao.selectBoardCount()");
		
		int cnt = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM board";
		
		try {
			stmt = conn.prepareStatement(sql);
			
			System.out.println("stmt --- " + stmt);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			System.out.println("cnt --- " + cnt);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return cnt;
	}
	
	
}
