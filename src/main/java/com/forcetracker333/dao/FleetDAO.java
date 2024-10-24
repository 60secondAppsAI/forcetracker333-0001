package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Fleet;





public interface FleetDAO extends GenericDAO<Fleet, Integer> {
  
	List<Fleet> findAll();
	






}


