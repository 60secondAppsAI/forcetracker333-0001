package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.TacticalMap;





public interface TacticalMapDAO extends GenericDAO<TacticalMap, Integer> {
  
	List<TacticalMap> findAll();
	






}


