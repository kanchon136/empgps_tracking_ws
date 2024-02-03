package com.techEureka.accountBackend.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.EmployeeGpsTrackInfo;

@Repository
@Transactional
public class EmpGpsTrackinfoCustomRepository {
	public static final Logger log = LogManager.getLogger(EmpGpsTrackinfoCustomRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<EmployeeGpsTrackInfo> findAllEmpgpsTrackInfos() {

		String query = " select GPSDATA_ID, MKGPROF_NO,EMPLOYE_NO ,EMPLOYE_NM,"
				+ "  GPSDATA_DT,GPSDATA_TM,M_LATITUDE,M_LONGITDE,GPSDATA_FG,"
				+ "  SAUSERS_ID,GPSDTA_DTM,GPSLOCA_NM,BATERY_PCT,INTCXN_TYP,"
				+ "  PWRSV_MODE,LOCSHAR_TYP,TOOLTIPTXT,PROF_PHOTO " + " from  HRV_GPSDATA ";

		log.info("query" + query);

		List<EmployeeGpsTrackInfo> employeeGpsTrackInfos = jdbcTemplate.query(query, (rs, rownum) -> {

			EmployeeGpsTrackInfo employeeGpsTrackInfo = new EmployeeGpsTrackInfo(rs.getString("GPSDATA_ID"),
					rs.getString("MKGPROF_NO"), rs.getString("EMPLOYE_NO"), rs.getString("EMPLOYE_NM"),
					rs.getDate("GPSDATA_DT"), rs.getString("GPSDATA_TM"), rs.getFloat("M_LATITUDE"),
					rs.getFloat("M_LONGITDE"), rs.getInt("GPSDATA_FG"), rs.getString("SAUSERS_ID"),
					rs.getDate("GPSDTA_DTM"), rs.getString("GPSLOCA_NM"), rs.getString("BATERY_PCT"),
					rs.getString("INTCXN_TYP"), rs.getString("PWRSV_MODE"), rs.getString("LOCSHAR_TYP"),
					rs.getString("TOOLTIPTXT"), rs.getString("PROF_PHOTO"));

			return employeeGpsTrackInfo;
		});

		log.info("=====employeeGpsTrackInfos size==:"+employeeGpsTrackInfos.size());
		return employeeGpsTrackInfos;

	}

}
