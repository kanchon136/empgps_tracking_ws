package com.techEureka.accountBackend.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;
import com.techEureka.accountBackend.repository.EmpGpsTrackinfoCustomRepository;
import com.techEureka.accountBackend.service.EmployeeGpsTrackInfoService;

@RestController
@RequestMapping("/empTrackInfo")
public class EmpGpsTrackInfoController {
	public static final Logger log = LogManager.getLogger(EmpGpsTrackinfoCustomRepository.class);
	
	@Autowired
	private EmployeeGpsTrackInfoService employeeGpsTrackInfoService;
	
	@GetMapping("/allEmpGpsTrackInfos")
	public ResponseEntity<List<EmployeeGpsTrackInfo>> findAllEmployeeTracInfos(){
		try {
			return new ResponseEntity<List<EmployeeGpsTrackInfo>>(employeeGpsTrackInfoService.findAllEmployeeGpsTrackInfos(),HttpStatus.OK);	
		}catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
		}
		
	}
	
	@GetMapping("/findAllTrackInfosByEmpNo/{empno}")
	public ResponseEntity<List<EmployeeGpsTrackInfo>> findAllTrackInofsByEmpNo(@PathVariable("empno") String empno){
		try {
			return new ResponseEntity<List<EmployeeGpsTrackInfo>>(employeeGpsTrackInfoService.findAlllTrackInfosByEmpNo(empno),HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
		}
		
	}
	
	@GetMapping("/lastGpsTrackInfoByEmpNo/{empno}")
	public ResponseEntity<Optional<EmployeeGpsTrackInfo>> lastGpsTrackInfoByEmpNo(@PathVariable("empno") String empno){
		try {
			return new ResponseEntity<Optional<EmployeeGpsTrackInfo>>(employeeGpsTrackInfoService.lastGpsTracInfoByEmpNo(empno),HttpStatus.OK);	
		}catch (Exception e) {
			log.error(e.getMessage());
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
