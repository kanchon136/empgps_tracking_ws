package com.techEureka.accountBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.UsersRole;

@Repository
@Transactional
public interface UsersRoleRepo extends JpaRepository<UsersRole, Long> {

	@Modifying
	@Query(value = "UPDATE user_role SET tx_role_name = :role, tx_spec_page_permission = :specPagePermission WHERE tx_user_id_key = :userId", nativeQuery = true)
	void assignRoleForUser(@Param("role") String role, @Param("specPagePermission") String specPagePermission,
			@Param("userId") Long userId);

	@Query("select ur.verifyFlg from UsersRole ur where ur.userName = :userName")
	int verifyFlg(String userName);

	@Query("select ur.userName from UsersRole ur where ur.userName = :userName")
	String userName(String userName);

	@Modifying
	@Query("UPDATE UsersRole ur SET ur.verifyFlg = 1 WHERE ur.usersInfo.id = :userid")
	void verifySignUpUser(@Param("userid") Long userid);

	@Modifying
	@Query("UPDATE UsersRole ur SET ur.specPagePermission = :specPagePermission WHERE ur.userName = :userName")
	void giveSpecPagePermission(@Param("userName") String userName,
			@Param("specPagePermission") String specPagePermission);

}
