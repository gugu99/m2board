package service;

import vo.Nice;

public interface INiceService {
	
	// 좋아요 올리기
	boolean addNice(Nice paramNice);
	// 좋아요 가져오기
	int getNiceCount(int boardNo); 
}
