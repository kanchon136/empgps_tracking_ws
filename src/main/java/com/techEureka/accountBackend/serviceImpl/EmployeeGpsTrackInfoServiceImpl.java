package com.techEureka.accountBackend.serviceImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;
import com.techEureka.accountBackend.repository.EmpGpsTrackinfoCustomRepository;
import com.techEureka.accountBackend.service.EmployeeGpsTrackInfoService;

@Service
public class EmployeeGpsTrackInfoServiceImpl implements EmployeeGpsTrackInfoService{
	
 public static final Logger log = LogManager.getLogger(EmpGpsTrackinfoCustomRepository.class);
 
	
	@Autowired
	private EmpGpsTrackinfoCustomRepository  empGpsTrackinfoCustomRepository;

	@Override
	public List<EmployeeGpsTrackInfo> findAllEmployeeGpsTrackInfos() {
		 
		Map<String, EmployeeGpsTrackInfo> checkduplilist =empGpsTrackinfoCustomRepository.findEmpgpsTrackInfosByEmpno()
				.parallelStream().filter(emp-> emp.getEmployeNo() != null)
				  .collect(Collectors.toMap(EmployeeGpsTrackInfo:: getEmployeNo, emp-> emp, (existing, replacement)-> existing ));
	 
		return checkduplilist.values()
				.parallelStream().filter(e-> e.getEmployeNo()!= null)
				  .sorted(Comparator.comparing(EmployeeGpsTrackInfo::getEmployeNo,
						   Comparator.nullsLast(String::compareToIgnoreCase))).toList();
		 
	}

	@Override
	public Optional<EmployeeGpsTrackInfo> findEmployeeTrackInfoByEmployeNo(String empNo) {
		Optional<EmployeeGpsTrackInfo> EmployeeGpsTrackInfo =empGpsTrackinfoCustomRepository.findEmpgpsTrackInfosByEmpno()
				.parallelStream().filter(em-> em.getEmployeNo() != null && em.getEmployeNo().equalsIgnoreCase(empNo))
				  .findFirst();
		
		if(EmployeeGpsTrackInfo.isPresent()) {
			return EmployeeGpsTrackInfo;
		}else {
			return Optional.empty();
		}
		
		
	}

	
	@Override
	public List<EmployeeGpsTrackInfo> findAlllTrackInfosByEmpNo(String empNo) {
	  
		 return empGpsTrackinfoCustomRepository.findEmpgpsTrackInfosByEmpno()
				 .parallelStream().filter(em-> em.getEmployeNo() != null && em.getEmployeNo()
				 .equalsIgnoreCase(empNo)).sorted(Comparator.comparing(EmployeeGpsTrackInfo::getEmployeNo))
				 .toList();
		 
	}

	@Override
	public Optional<EmployeeGpsTrackInfo> lastGpsTracInfoByEmpNo(String empno) {
		 Optional<EmployeeGpsTrackInfo> latgpstracinfoByempno = empGpsTrackinfoCustomRepository
				 .findEmpgpsTrackInfosByEmpno().parallelStream()
				   .filter(emp-> emp.getEmployeNo()!= null)
				    .filter(e-> e.getEmployeNo().equalsIgnoreCase(empno))
				      .max((data1,data2)-> data1.getGpsDataDateTime().compareTo(data2.getGpsDataDateTime()));
		 
		 if(latgpstracinfoByempno.isPresent()) {
			 return latgpstracinfoByempno;
			 
		 }
		 else {
			 return Optional.empty();
			
		}
		
	}  

}
