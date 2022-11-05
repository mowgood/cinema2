package com.cinema.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.web.exception.IdPasswordException;
import com.cinema.web.service.UserService;
import com.cinema.web.util.AuthInfo;
import com.cinema.web.util.LoginCommand;

@Controller
public class LoginController {
	
	@RequestMapping(value="/user/login", method=RequestMethod.GET)
	public ModelAndView loginForm(LoginCommand loginCommand,
			@CookieValue(value="REMEMBER", required=false) Cookie rememberCookie) throws Exception {
		
		if(rememberCookie!=null) { // 쿠키가 있으면 쿠키에 있는 id와 rememberId의 값을 보냄
			loginCommand.setId(rememberCookie.getValue());
			loginCommand.setRememberId(true);
		}
		
		ModelAndView mv = new ModelAndView("user/loginForm");
		return mv;
		
	}
	
	@Resource(name="userService")
	private UserService userSer;
	
	@RequestMapping(value="/user/login", method=RequestMethod.POST)
	public ModelAndView loginSuccess(@Valid LoginCommand loginCommand, BindingResult bindingResult,
			HttpSession session, HttpServletResponse response) throws Exception {
		
		if(bindingResult.hasErrors()) {
			ModelAndView mv = new ModelAndView("user/loginForm");
			return mv;
		}
		
		try {
			AuthInfo authInfo = userSer.loginAuth(loginCommand);
			session.setAttribute("authInfo", authInfo);
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getId());
			rememberCookie.setPath("/");
			if(loginCommand.isRememberId()) {
				rememberCookie.setMaxAge(60*60*24*7); //아이디 기억 체크 시 7일 기억
			} else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie); //쿠키 적용
		
		} catch (IdPasswordException e) {
			bindingResult.rejectValue("pw", "notMatch", "아이디와 비밀번호가 맞지 않습니다.");
			ModelAndView mv = new ModelAndView("user/loginForm");
			return mv;
		}
		
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
	
	@RequestMapping("/user/logout")
	public ModelAndView logout(HttpSession session) {
		session.invalidate();
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}

