package com.techEureka.accountBackend.serviceImpl;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.Role;
import com.techEureka.accountBackend.entity.UsersInfo;
import com.techEureka.accountBackend.entity.UsersRole;
import com.techEureka.accountBackend.model.AddRoleModel;
import com.techEureka.accountBackend.model.AssignRoleModel;
import com.techEureka.accountBackend.model.SignUpModel;
import com.techEureka.accountBackend.model.SpecPagePermissionModel;
import com.techEureka.accountBackend.model.UpdatePagePermissionModel;
import com.techEureka.accountBackend.model.UpdateUserInfoModel;
import com.techEureka.accountBackend.repository.RoleRepo;
import com.techEureka.accountBackend.repository.UsersInfoRepo;
import com.techEureka.accountBackend.repository.UsersRoleRepo;
import com.techEureka.accountBackend.service.AdminService;
import com.techEureka.accountBackend.utils.BaseResponse;

import ch.qos.logback.classic.Logger;

@Service
public class AdminServiceImpl implements AdminService {

	static Logger log = (Logger) LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	UsersInfoRepo usersInfoRepo;

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	UsersRoleRepo usersRoleRepo;

	@Override
	public BaseResponse signUp(SignUpModel request) {
		BaseResponse baseResponse = new BaseResponse();

		UsersInfo usersInfo = signUpModelToUsersInfoEntity(request);
		try {
			usersInfoRepo.save(usersInfo);
			baseResponse.setMessage("Success");

		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public BaseResponse allUserInfos() {

		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setUsersInfo(usersInfoRepo.findAll());

		return baseResponse;
	}

	@Override
	public BaseResponse addRole(AddRoleModel request) {

		BaseResponse baseResponse = new BaseResponse();

		Role role = addRoleModelToroleEntity(request);
		try {
			roleRepo.save(role);
			baseResponse.setMessage("Success");

		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	public UsersInfo signUpModelToUsersInfoEntity(SignUpModel request) {

		UsersInfo usersInfo = new UsersInfo();
		BeanUtils.copyProperties(request, usersInfo);
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		usersInfo.setPassword(encoder.encode(request.getPassword()));

		UsersRole usersRole = new UsersRole();
		usersRole.setUserName(request.getUserName());
		usersRole.setDefaultRoleId(request.getDefaultRoleId());
		usersRole.setRoleName(request.getRoleName());
		usersRole.setSpecPagePermission(request.getSpecPagePermission());
		usersRole.setUsersInfo(usersInfo);

		usersInfo.setUsersRole(usersRole);
		return usersInfo;
	}

	public Role addRoleModelToroleEntity(AddRoleModel request) {

		Role role = new Role();
		BeanUtils.copyProperties(request, role);
		return role;
	}

	@Override
	public BaseResponse assignRole(AssignRoleModel request) {
		BaseResponse baseResponse = new BaseResponse();

		try {

			usersRoleRepo.assignRoleForUser(request.getRole(), request.getSpecPagePermission(), request.getUserId());
			baseResponse.setMessage("Success");

		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public BaseResponse allRole() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setRoles(roleRepo.findAll());
		return baseResponse;
	}

	@Override
	public BaseResponse role(Long request) {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setRole(roleRepo.getById(request));
		return baseResponse;
	}

	@Override
	public BaseResponse userInfo(String userName) {
		BaseResponse baseResponse = new BaseResponse();
		try {

			Long id = usersInfoRepo.findUserByUserName(userName);
			baseResponse.setUsersInfoo(usersInfoRepo.getById(id));

		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		try {
			baseResponse.setRole(roleRepo.getById(baseResponse.getUsersInfoo().getUsersRole().getDefaultRoleId()));
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public boolean checkVerify(String userName) {

		if (usersRoleRepo.verifyFlg(userName) == 1 && usersRoleRepo.userName(userName).contentEquals(userName)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public BaseResponse allSignUp() {
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setUsersInfo(usersInfoRepo.findAllSignUp());

		return baseResponse;
	}

	@Override
	public BaseResponse verifyAssignUser(Long userId) {
		BaseResponse baseResponse = new BaseResponse();

		try {
			usersRoleRepo.verifySignUpUser(userId);
			baseResponse.setMessage("User Verify succesful");
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public BaseResponse deleteUser(Long userId) {
		BaseResponse baseResponse = new BaseResponse();

		try {
			usersInfoRepo.deleteById(userId);
			baseResponse.setMessage("User deleted succesfully");
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	@Transactional
	public BaseResponse updateUserInfo(UpdateUserInfoModel request) {
		// log.info(request);
		BaseResponse baseResponse = new BaseResponse();

		try {
			usersInfoRepo.updateUsersInfo(request.getUserId(), request.getUserName(), request.getEmail(),
					request.getPhone(), request.getPassword(), request.getName(), request.getDob(), request.getBlood(),
					request.getAddress());
			baseResponse.setMessage("User updated succesfully");
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public BaseResponse updatePagePermission(UpdatePagePermissionModel request) {
		BaseResponse baseResponse = new BaseResponse();

		try {
			roleRepo.updatePagePermission(request.getRoleId(), request.getPagePermission());
			baseResponse.setMessage("Page Permission updated succesfully");
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

	@Override
	public BaseResponse giveSpecPagePermission(SpecPagePermissionModel request) {
		BaseResponse baseResponse = new BaseResponse();

		try {
			usersRoleRepo.giveSpecPagePermission(request.getUserName(), request.getSpecPagePermission());
			baseResponse.setMessage("SpecPagePermission updated succesfully");
		} catch (Exception ex) {
			baseResponse.setMessage(ex.getMessage());
		}

		return baseResponse;
	}

}
