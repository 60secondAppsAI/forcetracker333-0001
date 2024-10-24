package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Mission;





public interface MissionDAO extends GenericDAO<Mission, Integer> {
  
	List<Mission> findAll();
	






}


