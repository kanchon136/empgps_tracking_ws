package com.techEureka.accountBackend.serviceImpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.HrvDesignationInfo;
import com.techEureka.accountBackend.repository.HrvDesignationInfoCustomRepository;
import com.techEureka.accountBackend.service.HrvDesignationInfoService;

@Service
public class HrvDesignationInfoServiceImpl implements HrvDesignationInfoService {

	@Autowired
	private HrvDesignationInfoCustomRepository designationInfoCustomRepository;

	@Override
	public List<HrvDesignationInfo> findAllDesignationInfosByAsstatusFlag(int statusfalg) {
		return designationInfoCustomRepository.findAllHrvDesignationInfos().parallelStream()
				.filter(e -> e.getAsStatusFlag() != null && e.getAsStatusFlag() == 1)
				.sorted(Comparator.comparing(HrvDesignationInfo::getUserDslNo, Comparator.nullsLast(Long::compareTo)))
				.toList();
	}
}
