package com.techEureka.accountBackend.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.techEureka.accountBackend.entity.UsersInfo;

@Repository
@Transactional
public interface UsersInfoRepo extends JpaRepository<UsersInfo, Long> {

	UsersInfo findByUserName(String userName);

	@Query("select ui.id from UsersInfo ui where ui.userName = :userName")
	Long findUserByUserName(@Param("userName") String userName);

	@Query("SELECT ui.email FROM UsersInfo ui JOIN ui.usersRole ur WHERE ur.defaultRoleId IN :defaultRoleIds")
	List<String> findEmailByDefaultRoleIds(@Param("defaultRoleIds") List<Long> defaultRoleIds);

	@Query("SELECT ui.email FROM UsersInfo ui WHERE ui.id IN :userIds")
	List<String> findEmailByUserIds(@Param("userIds") List<Long> ids);

	@Query("SELECT ui.email FROM UsersInfo ui JOIN ui.usersRole ur WHERE ur.defaultRoleId = :hosRoleId")
	List<String> findEmailOfHOS(@Param("hosRoleId") Long hosRoleId);

	@Query("SELECT ui FROM UsersInfo ui JOIN ui.usersRole ur WHERE ur.defaultRoleId IN :defaultRoleIds")
	List<UsersInfo> findUserByDefaultRoleId(@Param("defaultRoleIds") Long defaultRoleIds);

	@Query("SELECT ui FROM UsersInfo ui JOIN ui.usersRole ur WHERE ur.verifyFlg=0")
	List<UsersInfo> findAllSignUp();

	@Modifying
	@Query(value = "UPDATE user_info ui SET ui.tx_user_name = :userName, ui.tx_email = :email, ui.tx_phone = :phone, ui.tx_password = :password, ui.tx_name = :name, ui.tx_birth_date = :dob, ui.tx_blood_group = :blood, ui.tx_address = :address WHERE ui.tx_user_id = :userId", nativeQuery = true)
	void updateUsersInfo(@Param("userId") Long userId, @Param("userName") String userName, @Param("email") String email,
			@Param("phone") String phone, @Param("password") String password, @Param("name") String name,
			@Param("dob") Date dob, @Param("blood") String blood, @Param("address") String address);

}
