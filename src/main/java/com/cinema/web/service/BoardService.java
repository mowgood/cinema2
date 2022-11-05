package com.cinema.web.service;

import java.util.List;
import java.util.Map;

import com.cinema.web.common.common.Criteria;

public interface BoardService {

	void insertBoard(Map<String, Object> items);

	void updateBoard(Map<String, Object> map);

	List<Map<String, Object>> selectBoardList(Criteria cri);
	
	int countBoardListTotal();

    Map<String, Object> viewBoardDetail(int idx);

    void deleteBoard(int idx);

    Map<String, Object> selectBoardDetail(int idx);

}

