package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.IntelligenceReport;





public interface IntelligenceReportDAO extends GenericDAO<IntelligenceReport, Integer> {
  
	List<IntelligenceReport> findAll();
	






}


