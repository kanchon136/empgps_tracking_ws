package com.techEureka.accountBackend.service;

import java.util.List;

import com.techEureka.accountBackend.entity.HrvDesignationInfo;

public interface HrvDesignationInfoService {

	public List<HrvDesignationInfo> findAllDesignationInfosByAsstatusFlag(int statusfalg);

}
