package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Surveillance;





public interface SurveillanceDAO extends GenericDAO<Surveillance, Integer> {
  
	List<Surveillance> findAll();
	






}


