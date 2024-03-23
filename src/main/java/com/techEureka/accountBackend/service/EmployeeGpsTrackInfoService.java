package com.techEureka.accountBackend.service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;

public interface EmployeeGpsTrackInfoService {
	List<EmployeeGpsTrackInfo> findAllEmployeeGpsTrackInfos();

	Optional<EmployeeGpsTrackInfo> findEmployeeTrackInfoByEmployeNo(String empNo);

	List<EmployeeGpsTrackInfo> findAlllTrackInfosByEmpNo(String empNo);

	Optional<EmployeeGpsTrackInfo> lastGpsTracInfoByEmpNo(String empno);

	List<EmployeeGpsTrackInfo> findAllEmpTrackInfosByGpsdataDate(Date date);

	List<EmployeeGpsTrackInfo> findAllEmpTrackInfosByEmnoAndGpsdate(String empno, String gpsDate);

	List<?> findAllGpsTrackinfosbyDesigAndDate(String desig, String date);

	List<?> findAllGpsTrackinfosByDesignation(String designation);
	
	List<EmployeeGpsTrackInfo> findAllTrackinfosByBetweenTime(LocalTime starttime , LocalTime endtime);
    
	Optional<EmployeeGpsTrackInfo> findEmpGpsInfoByMkgNoAndDataTime(String emoNo, String dataTime);
	List<?> findEmpGpsInfosByDesigAndMkgprofEqualsMkzmCode(String designo,String mkgPfofNo);
}
