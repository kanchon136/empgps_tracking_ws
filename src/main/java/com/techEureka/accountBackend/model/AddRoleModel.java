package com.techEureka.accountBackend.model;

import lombok.Data;

@Data
public class AddRoleModel {

	private String roleName;
	private String rolePermission;
	private String pagePermission;
	private String elementPermisiion;

}
