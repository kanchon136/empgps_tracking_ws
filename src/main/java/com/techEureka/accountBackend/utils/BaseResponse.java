package com.techEureka.accountBackend.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.techEureka.accountBackend.entity.Role;
import com.techEureka.accountBackend.entity.UsersInfo;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

	String message;

	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
	UsersInfo usersInfoo;
	List<UsersInfo> usersInfo;

	@JsonIgnoreProperties({ "hibernateLazyInitializer" })
	Role role;
	List<Role> roles;

}
