package com.cinema.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.web.service.MovieService;

@Controller
public class MovieController {
	
	@Resource(name="movieService")
	private MovieService movieService;

	@RequestMapping(value="/movie/list")
	public ModelAndView openMovieList() throws Exception {
		
		ModelAndView mv = new ModelAndView("/movie/movieList");
		
		List<Map<String, Object>> movies = movieService.selectMovieList();
		mv.addObject("movies", movies);
		
		return mv;
	}

	// @RequestMapping(value="/movie/reserve/{mvcode}") //예매
	// public ModelAndView movieReserve(@PathVariable int mvcode) throws Exception {
		
	// }

	@RequestMapping(value="/movie/reserve") //예매
	public ModelAndView movieReserve() throws Exception {
		
		ModelAndView mv = new ModelAndView("/movie/movieReserve");
		
		List<Map<String, Object>> movies = movieService.selectMovieList();
		List<Map<String, Object>> theaters = movieService.selectTheaters();	
		List<Map<String, Object>> schedules = movieService.selectSchedules();
		
		mv.addObject("movies", movies);
		mv.addObject("theaters", theaters); //추후 영화 빼고 극장, 스케줄 DB는 삭제
		mv.addObject("schedules", schedules); //추후 영화 빼고 극장, 스케줄 DB는 삭제

		return mv;
	}
	
	@RequestMapping(value="/movie/getThList") //상영관 출력
	@ResponseBody
	public String getThList(String mvcode) {
		System.out.println("조회할 영화 코드 : " + mvcode);
		
		String ThList = movieService.getThList(mvcode);
		
		return ThList;
	}
	
	@RequestMapping(value="/movie/getScDateList") //날짜 출력
	@ResponseBody
	public String getScDateList(String mvcode, String thcode) {
		System.out.println("조회할 영화 코드 : " + mvcode + " 조회할 극장 코드 : " + thcode);
		
		String ScDateList = movieService.getScDateList(mvcode, thcode);
		
		return ScDateList;
	}
	
	@RequestMapping(value="/movie/getScTimeList") //시간 출력
	@ResponseBody
	public String getScTimeList(String mvcode, String thcode, String scdate) {
		System.out.println("조회할 영화 코드 : " + mvcode + " 조회할 극장 코드 : " + thcode + "조회할 날짜 : " + scdate);
		
		String ScTimeList = movieService.getScTimeList(mvcode, thcode, scdate);
		
		return ScTimeList;
	}

	@RequestMapping(value="/movie/reserve", method=RequestMethod.POST)
	public ModelAndView mvReservation(ReserveForm reserveForm) throws Exception {

		ModelAndView mv = new ModelAndView("/home");
		Map<String, Object> items = new HashMap<>();
		items.put("rethcode", Integer.parseInt(reserveForm.getRethcode()));
		items.put("rescdate", reserveForm.getRescdate() + " " + reserveForm.getResctime());
		items.put("rescroom", reserveForm.getRescroom());
		items.put("resctime", reserveForm.getResctime());

		System.out.println(reserveForm.getRethcode() + reserveForm.getResctime());

		movieService.insertReservation(items);
		
		return mv;
	}

}
