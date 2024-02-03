package com.techEureka.accountBackend.service;

import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.model.AddRoleModel;
import com.techEureka.accountBackend.model.AssignRoleModel;
import com.techEureka.accountBackend.model.SignUpModel;
import com.techEureka.accountBackend.model.SpecPagePermissionModel;
import com.techEureka.accountBackend.model.UpdatePagePermissionModel;
import com.techEureka.accountBackend.model.UpdateUserInfoModel;
import com.techEureka.accountBackend.utils.BaseResponse;

@Service
public interface AdminService {

	BaseResponse signUp(SignUpModel request);

	BaseResponse giveSpecPagePermission(SpecPagePermissionModel request);

	BaseResponse allUserInfos();

	BaseResponse userInfo(String userName);

	BaseResponse addRole(AddRoleModel request);

	BaseResponse allRole();

	BaseResponse role(Long request);

	BaseResponse assignRole(AssignRoleModel request);

	boolean checkVerify(String userName);

	BaseResponse allSignUp();

	BaseResponse verifyAssignUser(Long userId);

	BaseResponse deleteUser(Long userId);

	BaseResponse updateUserInfo(UpdateUserInfoModel request);

	BaseResponse updatePagePermission(UpdatePagePermissionModel request);

}
