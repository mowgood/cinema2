package com.cinema.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cinema.web.common.dao.AbstractDAO;

@Repository("movieDAO")
public class MovieDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMovieList() {
		return (List<Map<String, Object>>)selectList("movie.selectMovieList");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTheaterList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("movie.selectTheater", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectScheduleList(Map<String, Object> map) {
		return (List<Map<String, Object>>)selectList("movie.selectSchedule", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTheaters() {
		return (List<Map<String, Object>>)selectList("movie.selectTheaters");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectSchedules() {
		return (List<Map<String, Object>>)selectList("movie.selectSchedules");
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectTheaterList(String mvcode) {
		return (List<Map<String, Object>>)selectList("movie.selectThList", mvcode);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectScDateList(String mvcode, String thcode) {	
		Map<String, String> map = new HashMap<>();
		map.put("mvcode", mvcode);
		map.put("thcode", thcode);
		
		return (List<Map<String, Object>>)selectList("movie.selectScDateList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectScTimeList(String mvcode, String thcode, String scdate) {
		Map<String, String> map = new HashMap<>();
		map.put("mvcode", mvcode);
		map.put("thcode", thcode);
		map.put("scdate", scdate);
		return (List<Map<String, Object>>)selectList("movie.selectScTimeList", map);
	}

    public void insertReservation(Map<String, Object> items) {
		insert("movie.insertReservation", items);
    }

   
}

