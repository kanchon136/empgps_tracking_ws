package com.techEureka.accountBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.model.AddRoleModel;
import com.techEureka.accountBackend.model.AssignRoleModel;
import com.techEureka.accountBackend.model.SignUpModel;
import com.techEureka.accountBackend.model.SpecPagePermissionModel;
import com.techEureka.accountBackend.model.UpdatePagePermissionModel;
import com.techEureka.accountBackend.model.UpdateUserInfoModel;
import com.techEureka.accountBackend.service.AdminService;
import com.techEureka.accountBackend.utils.BaseResponse;

@RestController
@RequestMapping("/tender/api/v1")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/signUp")
	public BaseResponse userSignUp(@RequestBody SignUpModel request) {
		return adminService.signUp(request);

	}

	@PostMapping("/giveSpecPagePermission")
	public BaseResponse giveSpecPagePermission(@RequestBody SpecPagePermissionModel request) {
		return adminService.giveSpecPagePermission(request);

	}

	@GetMapping("/allUserInfo")
	public BaseResponse allUserInfo() {

		return adminService.allUserInfos();
	}

	@GetMapping("/userInfo/{userName}")
	public BaseResponse userInfo(@PathVariable("userName") String userName) {
		return adminService.userInfo(userName);
	}

	@PostMapping("/addRole")
	public BaseResponse addRole(@RequestBody AddRoleModel request) {
		return adminService.addRole(request);

	}

	@GetMapping("/allRole")
	public BaseResponse allRole() {

		return adminService.allRole();
	}

	@GetMapping("/role/{roleId}")
	public BaseResponse role(@PathVariable("roleId") Long roleId) {
		return adminService.role(roleId);
	}

	@PostMapping("/assignRole")
	public BaseResponse assignRole(@RequestBody AssignRoleModel request) {
		return adminService.assignRole(request);

	}

	@GetMapping("/allSignUp")
	public BaseResponse allSignUp() {

		return adminService.allSignUp();
	}

	@GetMapping("/verifyUser/{userId}")
	public BaseResponse verifyUser(@PathVariable("userId") Long userId) {
		return adminService.verifyAssignUser(userId);

	}

	@GetMapping("/deleteUser/{userId}")
	public BaseResponse deleteUser(@PathVariable("userId") Long userId) {
		return adminService.deleteUser(userId);

	}

	@PostMapping("/updateUserInfo")
	public BaseResponse updateUserInfo(@RequestBody UpdateUserInfoModel request) {
		return adminService.updateUserInfo(request);

	}

	@PostMapping("/updatePagePermission")
	public BaseResponse updatePagePermission(@RequestBody UpdatePagePermissionModel request) {
		return adminService.updatePagePermission(request);

	}

}
