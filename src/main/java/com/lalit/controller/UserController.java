package com.lalit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lalit.ServiceImpl.UserServiceImpl;
import com.lalit.bindings.ResetPassword;
import com.lalit.entity.Users;
import com.lalit.service.JwtService;
import com.lalit.utils.RandomPassword;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserServiceImpl service;
	@Autowired
	private PasswordEncoder pwdencoder;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtService jwt;
	@Autowired
	private RandomPassword util;
	@PostMapping("/register")
	public String save(@RequestBody Users user) {
	         boolean valid= service.validUser(user);
	         if(valid) {
		                user.setPassword(util.generate(5));
		      service.email(user.getEmail(), user.getPassword());
		                
		              String encodedPwd= pwdencoder.encode(user.getPassword());
		                user.setPassword(encodedPwd);
		service.register(user);
		  return "User saved and pasword sent to ur email";
		}else return "username or email alreday registered";
	}
	            
	@GetMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user) {
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword());
		try { 
		Authentication authenticate = authManager.authenticate(token);
		  if(authenticate.isAuthenticated()) {
			  String jwtToken = jwt.generateToken(user.getName());
			           jwt.extractExpiration(jwtToken);
		  return new ResponseEntity<>(jwtToken, HttpStatus.OK);
		  }
		  }catch(Exception e) {
			  e.printStackTrace();
			  }
		  return new ResponseEntity<>("invalid credentials",HttpStatus.BAD_REQUEST);
}
	@GetMapping("/user1")
	public ResponseEntity<Users> getUserInfo() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    Users user = (Users) authentication.getPrincipal();
	    return ResponseEntity.ok(user);
	}
	@GetMapping("/reset")
		public String reset(ResetPassword password,HttpServletRequest request) {
		    boolean reset=service.resetPassword(password, request);
		    return "return password updated";
		    
		     
		
	}
	
		
	
	

}
