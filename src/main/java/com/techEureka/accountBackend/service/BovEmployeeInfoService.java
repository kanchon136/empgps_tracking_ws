package com.techEureka.accountBackend.service;

import java.util.List;
import java.util.Optional;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;

public interface BovEmployeeInfoService {

	List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(String desigCode);

	List<BovEmployeeInfo> findAllBovEmpInfos();
	List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgProfNo(String desigCode,String ptgProfNo);
	
	Optional<BovEmployeeInfo> findBovEmployeeByMkgprofNo(String mkgprofNo);

}
