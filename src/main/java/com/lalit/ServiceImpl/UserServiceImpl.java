package com.lalit.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lalit.Repo.UserRepo;
import com.lalit.bindings.ResetPassword;
import com.lalit.entity.Users;
import com.lalit.service.JwtService;
import com.lalit.service.UserService;
import com.lalit.utils.EmailSender;
import com.lalit.utils.RandomPassword;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo urepo;
	@Autowired
	private RandomPassword util;
	@Autowired 
	private EmailSender sender;
	@Autowired
     private JwtService jwtService;
	@Autowired
	private PasswordEncoder pwdencoder;
	
	@Override
	public void register(Users request) {
		   Users user =null;
		if(request.getId()!=null) {
			Optional<Users> optional = urepo.findById(request.getId());
			if(optional.isPresent()) {
				  user = optional.get(); 
			}
		} else {
			  user = new Users();
			 
		}
		BeanUtils.copyProperties(request, user);
		urepo.save(user);
		}
	@Override
	public boolean login(Users user) {
		Optional<Users> optional=urepo.findByEmailAndPassword(user.getEmail(),user.getPassword());
		if(optional.isPresent()) {
			return true;
		}return false;
		
	}
	@Override
	public boolean email(String to,String text) {
		  String   subject="ur temporary password is";
		sender.sendSimpleMessage(to, subject, text);
		   return true;
	}
	@Override
	public  boolean validUser(Users user) {
	         Users user1=  urepo.findUser(user.getName(), user.getEmail());
	         if(user1!=null) {
	        	 return false;
	         }return true;
		}
	@Override
	public boolean resetPassword(ResetPassword password,HttpServletRequest request) {
		
		
	        String authHeader = request.getHeader("Authorization");
	        String token = null;
	        String username = null;
	        if(authHeader != null && authHeader.startsWith("Bearer ")){
	            token = authHeader.substring(7);
	            username = jwtService.extractUsername(token);
	        }
	        Users user=new Users();
	           String encodedPassword= pwdencoder.encode(password.getNewPassword());
	                user.setPassword(encodedPassword);
	                user.setName(username);
	                urepo.save(user);
	           return  true;
	}
	

}
