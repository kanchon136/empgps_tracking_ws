package com.techEureka.accountBackend.repository;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.BovEmployeeInfo;

@Repository
@Transactional
public class BovEmployeeInfoCustomRepository {

	private static final Logger log = LogManager.getLogger(BovEmployeeInfoCustomRepository.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<BovEmployeeInfo> findAllBovEmployeeInfos() {
		String query = "SELECT MKGPROF_NO,EMPLOYE_NO,EMPLOYE_NM,DESIG_CODE,PTGPROF_NO,ACTVSTS_FG,MK_ZM_CODE" + " FROM BOV_EMPLYEE";
		log.info("query======:" + query);
		List<BovEmployeeInfo> bovEmployeeInfos = jdbcTemplate.query(query, (rs, rownum) -> {

			BovEmployeeInfo bovEmployeeInfo = new BovEmployeeInfo(rs.getString("MKGPROF_NO"),
					rs.getString("EMPLOYE_NO"), rs.getString("EMPLOYE_NM"), rs.getString("DESIG_CODE"),
					rs.getString("PTGPROF_NO"), rs.getInt("ACTVSTS_FG"),rs.getString("MK_ZM_CODE"));

			return bovEmployeeInfo;

		});

		return bovEmployeeInfos;

	}
	
	public List<BovEmployeeInfo> findAllBovEmpInfosParentAndChildByPtgprofNo(String ptgProfId) {
		String query = "SELECT MKGPROF_NO,EMPLOYE_NO,EMPLOYE_NM,DESIG_CODE,PTGPROF_NO,ACTVSTS_FG,MK_ZM_CODE FROM BOV_EMPLYEE where MKGPROF_NO ='"+ptgProfId+"'"
				+ " UNION ALL"
				+ " SELECT MKGPROF_NO,EMPLOYE_NO,EMPLOYE_NM,DESIG_CODE,PTGPROF_NO,ACTVSTS_FG,MK_ZM_CODE FROM BOV_EMPLYEE where PTGPROF_NO= '"+ptgProfId+"'";
		log.info("query======:" + query);
		List<BovEmployeeInfo> bovEmployeeInfos = jdbcTemplate.query(query, (rs, rownum) -> {

			BovEmployeeInfo bovEmployeeInfo = new BovEmployeeInfo(rs.getString("MKGPROF_NO"),
					rs.getString("EMPLOYE_NO"), rs.getString("EMPLOYE_NM"), rs.getString("DESIG_CODE"),
					rs.getString("PTGPROF_NO"), rs.getInt("ACTVSTS_FG"), rs.getString("MK_ZM_CODE"));

			return bovEmployeeInfo;

		});

		return bovEmployeeInfos;

	}
	
	
	public List<BovEmployeeInfo> findAllBovEmpInfosUnderDynamicDesigCode(String desigCode){
		String query ="SELECT DISTINCT MKGPROF_NO,EMPLOYE_NO,EMPLOYE_NM,DESIG_CODE,PTGPROF_NO,ACTVSTS_FG,MK_ZM_CODE FROM BOV_EMPLYEE E"
				+ " START WITH E.MKGPROF_NO='"+desigCode+"' CONNECT BY PRIOR E.MKGPROF_NO = E.PTGPROF_NO";
		log.info("=====findAllBovEmpInfosUnderDynamicDesigCode====Query:"+query);
		List<BovEmployeeInfo> bovEmploeeInfos = jdbcTemplate.query(query, (rs,rownum)->{
			BovEmployeeInfo bovEmployeeInfo = new BovEmployeeInfo(rs.getString("MKGPROF_NO"),
					rs.getString("EMPLOYE_NO"), rs.getString("EMPLOYE_NM"), rs.getString("DESIG_CODE"),
					rs.getString("PTGPROF_NO"), rs.getInt("ACTVSTS_FG"), rs.getString("MK_ZM_CODE"));
			return bovEmployeeInfo;
				 
		});
		return bovEmploeeInfos;
	}
	

}
