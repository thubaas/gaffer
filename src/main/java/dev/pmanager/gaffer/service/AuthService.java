package dev.pmanager.gaffer.service;

import dev.pmanager.gaffer.dto.SigninRequest;
import dev.pmanager.gaffer.dto.SigninResponse;
import dev.pmanager.gaffer.dto.UserDto;
import dev.pmanager.gaffer.model.User;

public interface AuthService {
	
	void signup(UserDto userDto);
	
	SigninResponse login(SigninRequest signinRequest);
	
	void logout();
	
	boolean doesEmailExist(String email);
	
	User getAuthenticatedUser(String email);
	
	User getAuthenticatedUser();
	
	boolean isAccountEnabled(String email);

}
