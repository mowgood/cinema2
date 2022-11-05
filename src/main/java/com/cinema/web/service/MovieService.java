package com.cinema.web.service;

import java.util.List;
import java.util.Map;

public interface MovieService {

	List<Map<String, Object>> selectMovieList();

	List<Map<String, Object>> selectTheater(Map<String, Object> map);

	List<Map<String, Object>> selectSchedule(Map<String, Object> map);

	List<Map<String, Object>> selectTheaters();

	List<Map<String, Object>> selectSchedules();

	String getThList(String mvcode);

	String getScDateList(String mvcode, String thcode);

	String getScTimeList(String mvcode, String thcode, String scdate);

	void insertReservation(Map<String, Object> items);
	
}

