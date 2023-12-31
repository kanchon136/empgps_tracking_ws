package com.techEureka.accountBackend.service;

import java.util.List;
import java.util.Optional;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;

public interface EmployeeGpsTrackInfoService {
	List<EmployeeGpsTrackInfo> findAllEmployeeGpsTrackInfos();
	
	Optional<EmployeeGpsTrackInfo> findEmployeeTrackInfoByEmployeNo(String empNo);
	List<EmployeeGpsTrackInfo> findAlllTrackInfosByEmpNo(String empNo);
	
	Optional<EmployeeGpsTrackInfo> lastGpsTracInfoByEmpNo(String empno);
	
	

}
