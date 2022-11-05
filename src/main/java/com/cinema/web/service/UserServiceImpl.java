package com.cinema.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cinema.web.dao.UserDAO;
import com.cinema.web.exception.EmailException;
import com.cinema.web.exception.IdException;
import com.cinema.web.exception.IdPasswordException;
import com.cinema.web.util.AuthInfo;
import com.cinema.web.util.LoginCommand;
import com.cinema.web.util.RegisterRequest;
import com.cinema.web.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource(name="userDAO")
 	private UserDAO userDAO;
	
	@Override
	public void register(RegisterRequest regReq) throws Exception {
		
		UserVO email = userDAO.selectByEmail(regReq.getEmail());
		if(email!=null) {
			throw new EmailException(regReq.getEmail()+" 중복 이메일");
		}
		
		UserVO id = userDAO.selectById(regReq.getId());
		if(id!=null) {
			throw new IdException(regReq.getId()+" 중복 아이디");
		}
		
		userDAO.insertUser(regReq);
	}
	
	@Override
	public AuthInfo loginAuth(LoginCommand loginCommand) throws Exception {
		UserVO user = userDAO.selectById(loginCommand.getId());
		if(user == null) {
			throw new IdPasswordException();
		}
		if(!user.matchPassword(loginCommand.getPw())) {
			throw new IdPasswordException();
		}
		return new AuthInfo(user.getID(), user.getNAME(), user.getGRADE());
	}
}

