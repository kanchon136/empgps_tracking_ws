package com.techEureka.accountBackend.model;

import java.util.Date;

import lombok.Data;

@Data
public class UpdateUserInfoModel {

	private Long userId;
	private String userName;
	private String email;
	private String phone;
	private String password;
	private String name;
	private Date dob;
	private String blood;
	private String address;

}
