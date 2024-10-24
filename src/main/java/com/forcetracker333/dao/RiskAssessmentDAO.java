package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.RiskAssessment;





public interface RiskAssessmentDAO extends GenericDAO<RiskAssessment, Integer> {
  
	List<RiskAssessment> findAll();
	






}


