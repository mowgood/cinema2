package com.cinema.web.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cinema.web.dao.MovieDAO;
import com.google.gson.Gson;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
	
	@Resource(name="movieDAO")
	private MovieDAO movieDAO;

	@Override
	public List<Map<String, Object>> selectMovieList() {
		return movieDAO.selectMovieList();
	}

	@Override
	public List<Map<String, Object>> selectTheater(Map<String, Object> map) {
		return movieDAO.selectTheaterList(map);
	}

	@Override
	public List<Map<String, Object>> selectSchedule(Map<String, Object> map) {
		return movieDAO.selectScheduleList(map);
	}

	@Override
	public List<Map<String, Object>> selectTheaters() {
		return movieDAO.selectTheaters();
	}

	@Override
	public List<Map<String, Object>> selectSchedules() {
		return movieDAO.selectSchedules();
	}

	@Override
	public String getThList(String mvcode) {
		List<Map<String, Object>> thList = movieDAO.selectTheaterList(mvcode);
		
		Gson gson = new Gson(); //gson 라이브러리 사용 변수 선언
		
		String thList_json = gson.toJson(thList);
		
		return thList_json;
	}

	@Override
	public String getScDateList(String mvcode, String thcode) {
		List<Map<String, Object>> scDateList = movieDAO.selectScDateList(mvcode, thcode);
		
		Gson gson = new Gson();
		
		String scDateList_json = gson.toJson(scDateList);
		
		return scDateList_json;
	}

	@Override
	public String getScTimeList(String mvcode, String thcode, String scdate) {
		List<Map<String, Object>> scTimeList = movieDAO.selectScTimeList(mvcode, thcode, scdate);
		
		Gson gson = new Gson();
		
		String scTimeList_json = gson.toJson(scTimeList);
			
		return scTimeList_json;
	}

	@Override
	public void insertReservation(Map<String, Object> items) {
		movieDAO.insertReservation(items);
	}
	
}
