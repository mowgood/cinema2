package com.cinema.web.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cinema.web.common.common.Criteria;
import com.cinema.web.common.dao.AbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends AbstractDAO {
	
	
	public void insertBoard(Map<String, Object> items) {
		insert("board.insertBoard", items);
	}

	public void updateBoard(Map<String, Object> map) {
		update("board.updateBoard", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBoardList(Criteria cri) {
		return (List<Map<String,Object>>)selectList("board.selectBoardList", cri);
	}

	public int countBoardList() {
		return (Integer)selectOne("board.countBoardList");
	}

	public void updateHitBoard(int idx) {
		update("board.updateHitBoard", idx);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> detailBoard(int idx) {
		return (Map<String, Object>) selectOne("board.detailBoard", idx);
	}

	public void deleteBoard(int idx) {
		delete("board.deleteBoard", idx);
	}

	public void updateBoard2(int idx) {
		update("board.updateBoard", idx);
	}

}

