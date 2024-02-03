package com.techEureka.accountBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BovEmployeeInfo {

	private String mkgProfNo;
	private String empNo;
	private String empName;
	private String designationCode;
	private String ptgprofno;
	private Integer activeStatusFlag;

}
