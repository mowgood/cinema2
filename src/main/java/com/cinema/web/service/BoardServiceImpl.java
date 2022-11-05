package com.cinema.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cinema.web.common.common.Criteria;
import com.cinema.web.dao.BoardDAO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	@Resource(name="boardDAO")
	private BoardDAO boardDAO;

	@Override
	public void updateBoard(Map<String, Object> map) {
		boardDAO.updateBoard(map);		
	}

	@Override
	public List<Map<String, Object>> selectBoardList(Criteria cri) {
		return boardDAO.selectBoardList(cri);
	}

	@Override
	public int countBoardListTotal() {
		return boardDAO.countBoardList();
	}

	@Override
	public Map<String, Object> viewBoardDetail(int idx) {
		boardDAO.updateHitBoard(idx);
		Map<String, Object> detail = boardDAO.detailBoard(idx);
		return detail;
	}

	@Override
	public void deleteBoard(int idx) {
		boardDAO.deleteBoard(idx);
	}

	@Override
	public Map<String, Object> selectBoardDetail(int idx) {
		return boardDAO.detailBoard(idx);
	}

	@Override
	public void insertBoard(Map<String, Object> items) {
		boardDAO.insertBoard(items);	
	}

	
}

