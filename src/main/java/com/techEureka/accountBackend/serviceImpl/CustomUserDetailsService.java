package com.techEureka.accountBackend.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.UsersInfo;
import com.techEureka.accountBackend.repository.UsersInfoRepo;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UsersInfoRepo repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsersInfo user = repository.findByUserName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}
}
