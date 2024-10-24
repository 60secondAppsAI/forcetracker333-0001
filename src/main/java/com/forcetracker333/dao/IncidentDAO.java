package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Incident;





public interface IncidentDAO extends GenericDAO<Incident, Integer> {
  
	List<Incident> findAll();
	






}


