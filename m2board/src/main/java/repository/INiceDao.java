package repository;

import java.sql.Connection;
import java.sql.SQLException;

import vo.Nice;

public interface INiceDao {

	// 좋아요 올리기
	int insertNice(Connection conn, Nice paramNice) throws SQLException;
	
	// 좋아요 수 가져오기
	int selectNiceCount(Connection conn, int boardNo) throws SQLException;
}
