package com.techEureka.accountBackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "role", indexes = { @Index(name = "default_role_id", columnList = "tx_default_role_id", unique = true) })
public class Role {

	@Id
	@SequenceGenerator(name = "default_role_sequence", sequenceName = "default_role_sequence", initialValue = 8000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "default_role_sequence")
	@Column(name = "tx_default_role_id", updatable = false)
	private Long id;

	@Column(name = "tx_role_name")
	private String roleName;

	@Column(name = "tx_role_permission")
	private String rolePermission;

	@Column(name = "tx_page_permission")
	private String pagePermission;

	@Column(name = "tx_element_permission")
	private String elementPermission;

	@Column(name = "tx_created_date")
	private Date createdDate = new Date();

	@Column(name = "tx_activeRole_flg")
	private int activeRole = 1;

}
