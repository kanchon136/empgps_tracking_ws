package com.techEureka.accountBackend.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;
import com.techEureka.accountBackend.repository.BovEmployeeInfoCustomRepository;
import com.techEureka.accountBackend.service.BovEmployeeInfoService;

@Service
public class BovEmployeeInfoServiceimpl  implements BovEmployeeInfoService{
	
	@Autowired
	private BovEmployeeInfoCustomRepository bovEmployeeInfoCustomRepository;

	@Override
	public List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(String desigCode,
			String ptgProfNo) {
		 
		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos().parallelStream()
				.filter(bov-> bov.getDesignationCode() != null  && bov.getActiveStatusFlag() !=null)
				.filter(f-> f.getDesignationCode().equalsIgnoreCase(desigCode) && f.getActiveStatusFlag()==1)
				.sorted(Comparator.comparing(BovEmployeeInfo::getDesignationCode,Comparator.nullsLast(String::compareToIgnoreCase)))
				.toList();
	}

	@Override
	public List<BovEmployeeInfo> findAllBovEmpInfos() {
		 
		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos();
	}

}
