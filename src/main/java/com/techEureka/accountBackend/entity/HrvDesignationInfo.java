package com.techEureka.accountBackend.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HrvDesignationInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long designationId;
	private String designationName;
	private Long ptgDigId;
	private String pdesgName;
	private Integer asStatusFlag;
	private Long userDslNo;
	private Long createdBy;
	private String createdAt;
	private Long updatedBy;
	private String updatedAt;
	private Long DigStrId;
	private String DsgstrName;
	private String DesigCode;
	private int recShowFlag;

}
