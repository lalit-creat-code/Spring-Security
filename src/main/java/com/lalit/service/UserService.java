package com.lalit.service;

import org.springframework.stereotype.Component;

import com.lalit.bindings.ResetPassword;
import com.lalit.entity.Users;

import jakarta.servlet.http.HttpServletRequest;
@Component
public interface UserService {
	
	public void register(Users user);
	public boolean login(Users user);
	public boolean email(String to,String text);
	public boolean validUser(Users user);
	public boolean  resetPassword(ResetPassword password,HttpServletRequest request);
	//public String resetPassword();

}
