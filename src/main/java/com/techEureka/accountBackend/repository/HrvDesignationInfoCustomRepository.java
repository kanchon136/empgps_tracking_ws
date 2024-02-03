package com.techEureka.accountBackend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.techEureka.accountBackend.entity.HrvDesignationInfo;

@Repository
@Transactional
public class HrvDesignationInfoCustomRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<HrvDesignationInfo> findAllHrvDesignationInfos() {
		String query = "select * from HRV_DESIGTN";

		List<HrvDesignationInfo> hrvDesignationInfos = jdbcTemplate.query(query, (rs, rowmap) -> {

			HrvDesignationInfo hrvDesignationInfo = new HrvDesignationInfo(rs.getLong("DESGTON_ID"),
					rs.getString("DESIG_NAME"), rs.getLong("PTG_DIG_ID"), rs.getString("PDESG_NAME"),
					rs.getInt("ASTATUS_FG"), rs.getLong("USERDSL_NO"), rs.getLong("CREATED_BY"),
					rs.getString("CREATED_AT"), rs.getLong("UPDATED_BY"), rs.getString("UPDATED_AT"),
					rs.getLong("DIG_STR_ID"), rs.getString("DSGSTRNAME"), rs.getString("DESIG_CODE"),
					rs.getInt("RECSHOW_FG"));

			return hrvDesignationInfo;

		});

		return hrvDesignationInfos;

	}

}
