package com.techEureka.accountBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.Role;

@Repository
@Transactional
public interface RoleRepo extends JpaRepository<Role, Long> {

	@Modifying
	@Query("update Role r set r.pagePermission = :pagePermission where id = :id")
	void updatePagePermission(Long id, String pagePermission);

}
