package com.springboot.influlims.service.interfaces;

public interface SecurityService {

	Object findLoggedInUser();

	String findLoggedInUsername();

	void autoLogin(String username, String password);

}
