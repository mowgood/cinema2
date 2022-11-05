package com.cinema.web.service;

import com.cinema.web.util.AuthInfo;
import com.cinema.web.util.LoginCommand;
import com.cinema.web.util.RegisterRequest;

public interface UserService {
	void register(RegisterRequest regReq) throws Exception;
	
	AuthInfo loginAuth(LoginCommand loginCommand) throws Exception;
}
