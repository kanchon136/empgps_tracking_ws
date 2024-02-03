package com.techEureka.accountBackend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.entity.HrvDesignationInfo;
import com.techEureka.accountBackend.repository.EmpGpsTrackinfoCustomRepository;
import com.techEureka.accountBackend.service.HrvDesignationInfoService;

@RestController
@RequestMapping("/hrvdesignation")
public class HrvDesignationInfoController {
	public static final Logger log = LogManager.getLogger(EmpGpsTrackinfoCustomRepository.class);

	@Autowired
	private HrvDesignationInfoService hrvDesignationInfoService;

	@GetMapping("/findAllHrvdesignationByAsStatus")
	public ResponseEntity<?> findAllHrvDesignationInfosByAsStatus() {
		try {
			return new ResponseEntity<List<HrvDesignationInfo>>(
					hrvDesignationInfoService.findAllDesignationInfosByAsstatusFlag(0), HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
