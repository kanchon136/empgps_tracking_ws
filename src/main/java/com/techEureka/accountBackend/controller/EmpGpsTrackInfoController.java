package com.techEureka.accountBackend.controller;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;
import com.techEureka.accountBackend.repository.EmpGpsTrackinfoCustomRepository;
import com.techEureka.accountBackend.service.EmployeeGpsTrackInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/empTrackInfo")
@RequiredArgsConstructor
public class EmpGpsTrackInfoController {
	public static final Logger log = LogManager.getLogger(EmpGpsTrackinfoCustomRepository.class);
	private final EmployeeGpsTrackInfoService employeeGpsTrackInfoService;

@GetMapping("/allEmpGpsTrackInfos")
public ResponseEntity<List<EmployeeGpsTrackInfo>> findAllEmployeeTracInfos() {
		try {
		return new ResponseEntity<List<EmployeeGpsTrackInfo>>(
		employeeGpsTrackInfoService.findAllEmployeeGpsTrackInfos(), HttpStatus.OK);
		} catch (Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

@GetMapping("/findAllTrackInfosByEmpNo/{empno}")
public ResponseEntity<List<EmployeeGpsTrackInfo>> findAllTrackInofsByEmpNo(@PathVariable("empno") String empno) {
		try {
		return new ResponseEntity<List<EmployeeGpsTrackInfo>>(
		employeeGpsTrackInfoService.findAlllTrackInfosByEmpNo(empno), HttpStatus.OK);
		} catch (Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

@GetMapping("/lastGpsTrackInfoByEmpNo/{empno}")
public ResponseEntity<Optional<EmployeeGpsTrackInfo>> lastGpsTrackInfoByEmpNo(@PathVariable("empno") String empno) {
		try {
		return new ResponseEntity<Optional<EmployeeGpsTrackInfo>>(
		employeeGpsTrackInfoService.lastGpsTracInfoByEmpNo(empno), HttpStatus.OK);
		} catch (Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

@GetMapping("/findAllEmpTrackInfosByDate/{date}")
public ResponseEntity<List<EmployeeGpsTrackInfo>> findAllEmpTrackInfosByDate(@PathVariable("date") String date) {
		try {
			if (date != null) {
			log.info("=========parameter date value===" + date);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date datev = format.parse(date);
			return new ResponseEntity<List<EmployeeGpsTrackInfo>>(employeeGpsTrackInfoService.findAllEmpTrackInfosByGpsdataDate(datev), HttpStatus.OK);
			} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

@GetMapping("/findEmpGpstracInfosbyDateAndEmpNoOrEmpno")
public ResponseEntity<?> findEmpGpstracInfosbyEmpNoAndGpsdate(
			@RequestParam(name = "empno", required = false) String empno,
			@RequestParam(name = "date", required = false) String date) {
		    log.info("========parameter when call findEmpGpstracInfosbyDateAndEmpNoOrEmpno=====empno======:" + empno+"  ==Date==:"+date);
		try {
			if ((empno!=null &&!"".equals(empno)&&!empno.isEmpty()) && ("".equals(date)||date.isEmpty())) {
			return new ResponseEntity<>(employeeGpsTrackInfoService.findAlllTrackInfosByEmpNo(empno),HttpStatus.OK);
			}
			if ((empno!=null&&!"".equals(empno)&&!empno.isEmpty()) && (date!=null&&!"".equals(date)&&!date.isEmpty())) {
			return new ResponseEntity<>(employeeGpsTrackInfoService.findAllEmpTrackInfosByEmnoAndGpsdate(empno, date), HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>("An error occurred....! please check log file", HttpStatus.SEE_OTHER);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

@GetMapping("/findAllEmpGpstrackInfosByDateAndDesig")
public ResponseEntity<?> findAllEmpGpstrackinfosByDateOrDesigOrDateAndDesig(
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "designation", required = false) String designation) {
		    log.info("=================== when call findAllEmpGpstrackInfosByDateAndDesig  Date and Designation::::"+"date======"+date+"designation======="+designation);
		try {
			if ((date != null)&& (designation != null)) {
			return new ResponseEntity<>(employeeGpsTrackInfoService.findAllGpsTrackinfosbyDesigAndDate(designation, date),HttpStatus.OK);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>("An error occurred....! please check log file", HttpStatus.SEE_OTHER);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	
@GetMapping("/findAllEmpgpstrackinfosByDesigNo/{DesigNo}")
public ResponseEntity<?> findAllEmpgpstrackinfosByDesigNo(@PathVariable("DesigNo") String DesigNo){
		try {
		return new ResponseEntity<>(employeeGpsTrackInfoService.findAllGpsTrackinfosByDesignation(DesigNo), HttpStatus.OK);
		} catch (Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

@GetMapping("/findAllTrackInfosByBetweenTimeNotUse")
public ResponseEntity<?> findAllTrackInfosByBetweenTime(@RequestParam("startTime") @DateTimeFormat(pattern = "hh:mm a") LocalTime startTime,
        @RequestParam("endTime") @DateTimeFormat(pattern = "hh:mm a") LocalTime endTime) {
	    log.info("==startTime Parameter===:"+startTime+" EndTime====:"+endTime);
	try {
		return new ResponseEntity<>(employeeGpsTrackInfoService.findAllTrackinfosByBetweenTime(startTime, endTime),HttpStatus.OK);
	} catch (Exception e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
@GetMapping("/findAllTrackInfosByBetweenTime")
public ResponseEntity<?> findAllTrackInfosByBetweenTimenew(@RequestParam("startTime")  String startTime,
       @RequestParam("endTime")  String endTime) {
	   log.info("==startTime Parameter===:"+startTime+" EndTime====:"+endTime);
	try {
	  LocalTime startTm = convertStringTo12HMlocalTime(startTime);
	  LocalTime endTm = convertStringTo12HMlocalTime(endTime);
	  return new ResponseEntity<>(employeeGpsTrackInfoService.findAllTrackinfosByBetweenTime(startTm,endTm),HttpStatus.OK);
	} catch (Exception e) {
	log.error(e.getMessage());
	 return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}	

private static LocalTime convertStringTo12HMlocalTime(String timeString) {
    DateTimeFormatter formatter24Hour = DateTimeFormatter.ofPattern("HH:mm");
    LocalTime localTime24Hour = LocalTime.parse(timeString, formatter24Hour);
    DateTimeFormatter formatter12Hour = DateTimeFormatter.ofPattern("hh:mm a");
    String formatted12HourString = localTime24Hour.format(formatter12Hour);
    return LocalTime.parse(formatted12HourString, formatter12Hour);
}

}
