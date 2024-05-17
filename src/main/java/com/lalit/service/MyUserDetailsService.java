package com.lalit.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lalit.Repo.UserRepo;
import com.lalit.entity.Users;


@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo urepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = urepo.findByName(username);

		return new User(user.getName(),user.getPassword(),Collections.emptyList());

	}

}