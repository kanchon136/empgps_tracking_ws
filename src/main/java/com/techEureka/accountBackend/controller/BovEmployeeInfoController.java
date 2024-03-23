package com.techEureka.accountBackend.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;
import com.techEureka.accountBackend.service.BovEmployeeInfoService;
import com.techEureka.accountBackend.service.EmployeeGpsTrackInfoService;

@RestController

@RequestMapping("/bovEmpInfo")
public class BovEmployeeInfoController {
	private static final Logger log = LogManager.getLogger(BovEmployeeInfoController.class);
	@Autowired
	private BovEmployeeInfoService bovEmployeeInfoService;
	@Autowired
	private EmployeeGpsTrackInfoService employeeGpsTrackInfoService;

	@GetMapping("/findAllBovEmpInfos")
	public List<BovEmployeeInfo> findAllBovEmpInfos() {
		return bovEmployeeInfoService.findAllBovEmpInfos();
	}

	@GetMapping("/findAllBovEmpInfosByDesigCode/{desigCode}")
	public ResponseEntity<List<BovEmployeeInfo>> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(
			@PathVariable("desigCode") String desigCode) {
		try {
			log.info("findAllBovEmpInfosByDesigCode size====:" + bovEmployeeInfoService
					.findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(desigCode).size());
			return new ResponseEntity<List<BovEmployeeInfo>>(
					bovEmployeeInfoService.findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(desigCode),
					HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/findAllBovEmpInfosByDesigCodeAndPtgprofNo/{ptgprofNo}")
	public ResponseEntity<List<BovEmployeeInfo>> findAllBovEmpInfosByDesigCodeAndPtgprofNo(
			 @PathVariable("ptgprofNo") String ptgprofNo) {
		try { 
			return new ResponseEntity<List<BovEmployeeInfo>>(
					bovEmployeeInfoService.findAllBovEmpInfosByDesigCodeAndPtgProfNo(null, ptgprofNo),HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/findAllBovEmployeeInfos")
	public ResponseEntity<?> findAllBovEmployeeInfos(){
		try {
		return new ResponseEntity<>(bovEmployeeInfoService.findAllBovEmpInfos(),HttpStatus.OK)	;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}
	
	@GetMapping("/findAllBovEmpInfosParentAndChildByPtgprofNo/{ptgProfNo}")
	public ResponseEntity<?> findAllByPtgProfParentAndChild(@PathVariable String ptgProfNo){
		try {
		return new ResponseEntity<>(bovEmployeeInfoService.findAllBovEmpInfosParentAndChildByPtgprofNo(ptgProfNo),HttpStatus.OK)	;
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
	}
	

}
