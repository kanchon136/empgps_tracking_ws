package com.techEureka.accountBackend.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGpsTrackInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gpsDataId;
	private String mkgProfNo;
	private String employeNo;
	private String employeName;
	private Date gpsDataDate;
	private String gpsDataTime;
	private Float mLatitute;
	private Float mLongitute;
	private int gpsDataFlag;
	private String saUsersId;
	private Date gpsDataDateTime;
	private String gpslocalName;
	private String bateryPct;
	private String intCxnType;
	private String pwrsvMode;
	private String locsharType;
	private String toolTipTxt;
	private String profPhoto;

}
