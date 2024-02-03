package com.techEureka.accountBackend.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;
import com.techEureka.accountBackend.repository.BovEmployeeInfoCustomRepository;
import com.techEureka.accountBackend.service.BovEmployeeInfoService;

@Service
public class BovEmployeeInfoServiceimpl implements BovEmployeeInfoService {
private static final Logger log = LogManager.getLogger(BovEmployeeInfoServiceimpl.class);
	@Autowired
	private BovEmployeeInfoCustomRepository bovEmployeeInfoCustomRepository;

	@Override
	public List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgproNoAndActiveStatus(String desigCode) {

		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos().parallelStream()
				.filter(bov -> bov.getDesignationCode() != null && bov.getActiveStatusFlag() != null)
				.filter(f -> f.getDesignationCode().equalsIgnoreCase(desigCode) && f.getActiveStatusFlag() == 1)
				.sorted(Comparator.comparing(BovEmployeeInfo::getDesignationCode,
						Comparator.nullsLast(String::compareToIgnoreCase)))
				.toList();
	}

	@Override
	public List<BovEmployeeInfo> findAllBovEmpInfos() {

		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos();
	}

	@Override
	public List<BovEmployeeInfo> findAllBovEmpInfosByDesigCodeAndPtgProfNo(String desigCode, String ptgProfNo) {
		
		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos().parallelStream()
				.filter(bov -> bov.getDesignationCode() != null && bov.getActiveStatusFlag() != null && bov.getPtgprofno() != null)
				.filter(f ->  f.getActiveStatusFlag() == 1 && f.getPtgprofno().equalsIgnoreCase(ptgProfNo))
				//.sorted(Comparator.comparing(BovEmployeeInfo::getDesignationCode,
						//Comparator.nullsLast(String::compareToIgnoreCase)))
				.toList();
	}

	@Override
	public Optional<BovEmployeeInfo> findBovEmployeeByMkgprofNo(String mkgprofNo) {
	       
		return bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos().parallelStream()
				.filter(f-> f.getMkgProfNo() != null && f.getMkgProfNo().equalsIgnoreCase(mkgprofNo)).findFirst();
	}
	
	private static LocalTime convertToTime(String time) {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("hh:mm a");
		return LocalTime.parse(time, formater);
	}
	
	private static boolean isTimeWithinRange(LocalTime checkTime, LocalTime starTime, LocalTime endTime) {
		return !checkTime.isBefore(starTime) && !checkTime.isAfter(endTime);
	}
	
	
	
	private static LocalDate toLacalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private static Date formatedDate(Date date) {
		Date converteddate = null;
		String convetdString = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			convetdString = format.format(date);
			converteddate = format.parse(convetdString);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return converteddate;
	}

	private static Date stringToDate(String date) {
		Date convertedParameterDate = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			convertedParameterDate = format.parse(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return convertedParameterDate;
	}	

}
