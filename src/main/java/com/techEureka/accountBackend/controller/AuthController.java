package com.techEureka.accountBackend.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.model.AuthRequest;
import com.techEureka.accountBackend.service.AdminService;
import com.techEureka.accountBackend.utils.JwtUtil;

@RestController
public class AuthController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	AdminService adminService;

	@PostMapping("/authenticate")
	public HashMap<String, String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		HashMap<String, String> hashmap = new HashMap<>();

		try {

			if (adminService.checkVerify(authRequest.getUserName()) == true) {
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
				hashmap.put("login", "success");
				hashmap.put("token", jwtUtil.generateToken(authRequest.getUserName()));
			} else {
				hashmap.put("login", "Not Verified");
			}

		} catch (Exception ex) {
			hashmap.put("login", "failed");
			System.out.println("inavalid username/password");
		}

		return hashmap;
	}

}
