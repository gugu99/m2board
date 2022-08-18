package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Nice;

public class NiceDao implements INiceDao{
	
	// 좋아요 수 가져오기
	@Override
	public int selectNiceCount(Connection conn, int boardNo) throws SQLException {
		System.out.println("--------------------BoardDao.selectNice()");
		int niceCnt = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) cnt FROM nice WHERE board_no = ?";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			
			System.out.println("stmt --- " + stmt);
			
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				niceCnt = rs.getInt("cnt");
			}
		}finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		System.out.println("niceCnt --- " + niceCnt);
		
		return niceCnt;
	}

	// 좋아요 올리기
	@Override
	public int insertNice(Connection conn, Nice paramNice) throws SQLException {
		System.out.println("--------------------BoardDao.updateNice()");
		int result = 0;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO nice (id, board_no, create_date) VALUES (?,?,NOW())";
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramNice.getId());
			stmt.setInt(2, paramNice.getBoardNo());
			
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
}
