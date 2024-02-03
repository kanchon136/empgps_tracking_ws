package com.techEureka.accountBackend.model;

import lombok.Data;

@Data
public class AssignRoleModel {

	private Long userId;
	private String role;
	private String specPagePermission;

}
