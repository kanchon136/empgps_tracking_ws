package com.techEureka.accountBackend.service;

import java.util.List;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;

public interface BovEmployeeInfoService {

	
List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(String desigCode,String ptgProfNo);

List<BovEmployeeInfo> findAllBovEmpInfos();
	

}
