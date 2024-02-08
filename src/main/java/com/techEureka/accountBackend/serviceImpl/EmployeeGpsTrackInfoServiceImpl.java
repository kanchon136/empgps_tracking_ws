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
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;
import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;
import com.techEureka.accountBackend.repository.BovEmployeeInfoCustomRepository;
import com.techEureka.accountBackend.repository.EmpGpsTrackinfoCustomRepository;
import com.techEureka.accountBackend.service.EmployeeGpsTrackInfoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeGpsTrackInfoServiceImpl implements EmployeeGpsTrackInfoService {
	public static final Logger log = LogManager.getLogger(EmployeeGpsTrackInfoServiceImpl.class);
	private final EmpGpsTrackinfoCustomRepository empGpsTrackinfoCustomRepository;
	private final BovEmployeeInfoCustomRepository bovEmployeeInfoCustomRepository;

	@Override
	public List<EmployeeGpsTrackInfo> findAllEmployeeGpsTrackInfos() {
		
		return empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos();
	}

	@Override
	public Optional<EmployeeGpsTrackInfo> findEmployeeTrackInfoByEmployeNo(String empNo) {
		Optional<EmployeeGpsTrackInfo> EmployeeGpsTrackInfo = empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos()
				.parallelStream().filter(em -> em.getMkgProfNo() != null && em.getMkgProfNo().equalsIgnoreCase(empNo)).findFirst();
		if (EmployeeGpsTrackInfo.isPresent()) {
			return EmployeeGpsTrackInfo;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<EmployeeGpsTrackInfo> findAlllTrackInfosByEmpNo(String empNo) {
		return empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos().parallelStream()
				.filter(em -> em.getMkgProfNo() != null && em.getMkgProfNo().equalsIgnoreCase(empNo))
				.sorted(Comparator.comparing(EmployeeGpsTrackInfo::getGpsDataTime,Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER))).toList();	
	}

	@Override
	public Optional<EmployeeGpsTrackInfo> lastGpsTracInfoByEmpNo(String empno) {
		Optional<EmployeeGpsTrackInfo> latgpstracinfoByempno = empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos()
				.parallelStream().filter(emp -> emp.getMkgProfNo() != null).filter(e -> e.getMkgProfNo().equalsIgnoreCase(empno))
				.max((data1, data2) -> data1.getGpsDataDateTime().compareTo(data2.getGpsDataDateTime()));
		if (latgpstracinfoByempno.isPresent()) {
			return latgpstracinfoByempno;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public List<EmployeeGpsTrackInfo> findAllEmpTrackInfosByGpsdataDate(Date date) {
		List<EmployeeGpsTrackInfo> datewaysEmployeeGpsTrackInfos = empGpsTrackinfoCustomRepository
				.findAllEmpgpsTrackInfos().parallelStream().filter(track -> track.getGpsDataDate() != null
						&& toLacalDate(formatedDate(track.getGpsDataDate())).equals(toLacalDate(date))).toList();
		         System.out.println("============================datewaysEmployeeGpsTrackInfos size===:"+datewaysEmployeeGpsTrackInfos.size());
		if (!datewaysEmployeeGpsTrackInfos.isEmpty() && datewaysEmployeeGpsTrackInfos != null) {
			Map<String, EmployeeGpsTrackInfo> checkDuplicateEmptrackinfos = datewaysEmployeeGpsTrackInfos
					.parallelStream().filter(f -> f.getMkgProfNo() != null)
					.collect(Collectors.toMap(EmployeeGpsTrackInfo::getMkgProfNo, emp -> emp, (ex, rep) -> ex));
			return checkDuplicateEmptrackinfos.values().parallelStream().filter(e -> e.getMkgProfNo() != null).toList();
		} else {
			return datewaysEmployeeGpsTrackInfos;
		}
	}

	@Override
	public List<EmployeeGpsTrackInfo> findAllEmpTrackInfosByEmnoAndGpsdate(String empno, String gpsDate) {
		return empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos().parallelStream()
				.filter(f -> f.getMkgProfNo() != null && f.getGpsDataDate() != null)
				.filter(e -> toLacalDate(formatedDate(e.getGpsDataDate())).equals(toLacalDate(stringToDate(gpsDate)))
				 && e.getMkgProfNo().equalsIgnoreCase(empno)).sorted(Comparator.comparing(EmployeeGpsTrackInfo::getGpsDataTime)).toList();
	}
	private static LocalTime convertToTime(String time) {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("hh:mm a");
		return LocalTime.parse(time, formater);
	}
	
  private static LocalTime convertStringTo12HMlocalTime(String LocaTime24hm) {
	  DateTimeFormatter formattOf24hm = DateTimeFormatter.ofPattern("HH:mm");
	  LocalTime localtime24Type = LocalTime.parse(LocaTime24hm, formattOf24hm);
	  DateTimeFormatter formatOf12hm = DateTimeFormatter.ofPattern("hh:mm a");
	  String string12hm = localtime24Type.format(formatOf12hm);
	  return LocalTime.parse(string12hm, formattOf24hm);
  }
	
	private static LocalTime convertlocalTimeTo12HourFormat(LocalTime localTime) {
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("hh:mm a");
		String stringFormatedTime = localTime.format(formater);
		return LocalTime.parse(stringFormatedTime, formater);
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

	@Override
	public List<?> findAllGpsTrackinfosbyDesigAndDate(String desig, String date) {
		List<BovEmployeeInfo> empByDesiglist = bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos()
				.parallelStream()
				.filter(e -> e.getDesignationCode() != null && e.getDesignationCode().equalsIgnoreCase(desig)).toList();
		if (!empByDesiglist.isEmpty()) {
			List<String> empIdList = empByDesiglist.parallelStream().filter(e -> e.getMkgProfNo() != null)
					.map(e -> e.getMkgProfNo()).toList();
			List<EmployeeGpsTrackInfo> infos = empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos()
					.parallelStream().filter(e -> e.getMkgProfNo() != null&& empByDesiglist.parallelStream().filter(k -> k.getMkgProfNo() != null)
					 .anyMatch(emp -> emp.getMkgProfNo().equalsIgnoreCase(e.getMkgProfNo()))&& toLacalDate(formatedDate(e.getGpsDataDate()))
					  .equals(toLacalDate(stringToDate(date)))).toList();
			return infos;
		} else {
			return empByDesiglist;
		}
	}

	@Override
	public List<?> findAllGpsTrackinfosByDesignation(String designation) {
		List<BovEmployeeInfo> bovemplistBydesignation = bovEmployeeInfoCustomRepository.findAllBovEmployeeInfos().parallelStream()
				.filter(f -> f.getDesignationCode() != null && f.getDesignationCode().equalsIgnoreCase(designation)).toList();
		if (!bovemplistBydesignation.isEmpty()) {
			return empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos().parallelStream()
					.filter(f -> f.getMkgProfNo() != null && bovemplistBydesignation.parallelStream()
					.filter(bov -> bov.getMkgProfNo() != null).anyMatch(p -> p.getMkgProfNo().equalsIgnoreCase(f.getMkgProfNo()))).toList();
		} else {
			return bovemplistBydesignation;
		}
	}

	@Override
	public List<EmployeeGpsTrackInfo> findAllTrackinfosByBetweenTime(LocalTime starttime, LocalTime endtime) {
		 
		return empGpsTrackinfoCustomRepository.findAllEmpgpsTrackInfos().parallelStream()
				.filter(e-> e.getGpsDataTime() != null && isTimeWithinRange(convertToTime(e.getGpsDataTime()), starttime, endtime))
				.toList();
	}
}
