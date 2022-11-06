package com.cinema.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cinema.web.exception.EmailException;
import com.cinema.web.exception.IdException;
import com.cinema.web.service.UserService;
import com.cinema.web.util.RegisterRequest;
import com.cinema.web.util.RegisterRequestValidator;

@Controller
public class UserController {

	@RequestMapping("/user/create")
	public ModelAndView createUser() throws Exception {
		ModelAndView mv = new ModelAndView("user/createUser");
		mv.addObject("registerRequest", new RegisterRequest());
		return mv;
	}
	
	@Resource(name="userService")
	private UserService userSer;
	
	@RequestMapping("/user/new")
	public ModelAndView newUser(RegisterRequest regReq, Errors errors) throws Exception {
		
		new RegisterRequestValidator().validate(regReq, errors);
		
		if(errors.hasErrors()) {
			ModelAndView mv = new ModelAndView("user/createUser");
			return mv;
		}
		
		try {
			userSer.register(regReq);
		} catch (EmailException e) {
			errors.rejectValue("email", "duplicate", "이미 가입된 이메일입니다.");
			ModelAndView mv = new ModelAndView("user/createUser");
			return mv;
		} catch (IdException e) {
			errors.rejectValue("id", "duplicate", "이미 가입된 아이디입니다.");
			ModelAndView mv = new ModelAndView("user/createUser");
			return mv;			
		}
		ModelAndView mv = new ModelAndView("user/new");
		
		return mv;
	}
}