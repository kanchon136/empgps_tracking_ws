package com.techEureka.accountBackend.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_info", indexes = { @Index(name = "user_id", columnList = "tx_user_id", unique = true),
		@Index(name = "user_name", columnList = "tx_user_name", unique = true) })
public class UsersInfo {

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", initialValue = 10000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@Column(name = "tx_user_id", updatable = false)
	private Long id;

	@Column(name = "tx_user_name")
	private String userName;

	@Column(name = "tx_email")
	private String email;

	@Column(name = "tx_phone")
	private String phone;

	@Column(name = "tx_created_date")
	private Date createdDate = new Date();

	@Column(name = "tx_password")
	private String password;

	@Column(name = "tx_name")
	private String name;

	@Column(name = "tx_birth_date")
	private Date dob;

	@Column(name = "tx_blood_group")
	private String blood;

	@Column(name = "tx_address")
	private String address;

	@OneToOne(mappedBy = "usersInfo", cascade = CascadeType.ALL)
	private UsersRole usersRole;

	private String teamName;
	private Long teamId;
	private int teamLeadFlg = 0;

}
