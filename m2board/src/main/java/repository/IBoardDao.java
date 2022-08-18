package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardDao {
	
	// 게시판 목록
	List<Map<String, Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws SQLException;
	int selectBoardCount(Connection conn) throws SQLException;
	
	// 게시판 글 상세보기
	Map<String, Object> selectBoardOne(Connection conn, int boardNo) throws SQLException;
	
	// 게시판 글쓰기
	int insertBoard(Connection conn, Board board) throws SQLException;
	
	// 조회수 올리기
	void updateRead(Connection conn, int boardNO) throws SQLException;
	
	// 게시판 글 수정하기
	
	// 게시판 글 삭제하기
}
