package com.techEureka.accountBackend.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
@Table(name = "user_role", indexes = { @Index(name = "role_id", columnList = "tx_role_id", unique = true) })
public class UsersRole {

	@Id
	@SequenceGenerator(name = "role_sequence", sequenceName = "role_sequence", initialValue = 5000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
	@Column(name = "tx_role_id", updatable = false)
	private Long id;

	@Column(name = "tx_user_name")
	private String userName;

	@Column(name = "tx_role_name")
	private String roleName;

	@Column(name = "tx_default_role_id")
	private Long defaultRoleId;

	@Column(name = "tx_spec_page_permission")
	private String specPagePermission;

	@Column(name = "tx_created_date")
	private Date createdDate = new Date();

	@OneToOne
	@JoinColumn(name = "tx_user_id_key", referencedColumnName = "tx_user_id")
	@JsonIgnore
	private UsersInfo usersInfo;

	@Column(name = "tx_verify_flg")
	private int verifyFlg = 0;

}
