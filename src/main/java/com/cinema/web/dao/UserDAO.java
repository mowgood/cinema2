package com.cinema.web.dao;

// 실제 데이터베이스에 접근하는 클래스
// AbstractDAO를 상속받아 메소드 사용
import org.springframework.stereotype.Repository;

import com.cinema.web.common.dao.AbstractDAO;
import com.cinema.web.util.RegisterRequest;
import com.cinema.web.vo.UserVO;

@Repository("userDAO")
public class UserDAO extends AbstractDAO {
	
	public UserVO selectByEmail(String email) throws Exception {
		return (UserVO)selectOne("user.selectByEmail", email);
	}
	
	public UserVO selectById(String id) throws Exception {
		return (UserVO)selectOne("user.selectById", id);
	}
	
	public void insertUser(RegisterRequest regReq) throws Exception {
		insert("user.register", regReq); // 회원 등록
	}
}

