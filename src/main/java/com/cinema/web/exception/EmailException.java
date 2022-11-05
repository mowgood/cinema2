package com.cinema.web.exception;

//회원 등록 예외처리
public class EmailException extends RuntimeException {
	public EmailException(String message) {
		super(message);
	}
}

