package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardService {

	// 게시판 글 목록
	// 반환값 : List<Board>, int lastPage
	Map<String, Object> getBoardList(int rowPerPage, int currentPage);
	
	// 게시판 글 상세보기
	Map<String, Object> getBoardOne(int boardNo);
	
	// 게시판 글 쓰기
	boolean addBoard(Board paramBoard);
	
	// 좋아요 올리기
//	int modifyNice(Connection conn, int boardNo) throws SQLException;
	
	// 좋아요 수 가져오기
//	int getNice(Connection conn, int boardNo) throws SQLException;
}
