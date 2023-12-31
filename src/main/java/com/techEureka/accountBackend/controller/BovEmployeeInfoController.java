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

@RestController

@RequestMapping("/bovEmpInfo")
public class BovEmployeeInfoController {
	private static final Logger log = LogManager.getLogger(BovEmployeeInfoController.class);
	
	@Autowired
	private BovEmployeeInfoService bovEmployeeInfoService;
	
	
	
	@GetMapping("/findAllBovEmpInfos")
	public List<BovEmployeeInfo> findAllBovEmpInfos(){
		return bovEmployeeInfoService.findAllBovEmpInfos();
		
	}
	
	
	
	@GetMapping("/findAllBovEmpInfosByDesigCode/{desigCode}")
	public ResponseEntity<List<BovEmployeeInfo>> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(@PathVariable("desigCode") String desigCode){
		
		try {
			log.info("findAllBovEmpInfosByDesigCode size====:"+bovEmployeeInfoService.findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(desigCode, null).size());
			return new ResponseEntity<List<BovEmployeeInfo>>(bovEmployeeInfoService.findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(desigCode, null),HttpStatus.OK);
			
		} catch (Exception e) {
		      log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

}
