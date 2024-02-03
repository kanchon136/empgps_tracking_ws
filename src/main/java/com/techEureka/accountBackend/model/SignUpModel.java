package com.techEureka.accountBackend.model;

import java.util.Date;

import lombok.Data;

@Data
public class SignUpModel {

	private String userName;
	private String email;
	private String phone;
	private String password;
	private String name;
	private Date dob;
	private String blood;
	private String address;
	private String roleName;
	private Long defaultRoleId;
	private String specPagePermission;
	private String teamName;
	private Long teamId;

}
