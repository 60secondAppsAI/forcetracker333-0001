package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.CommandCenter;





public interface CommandCenterDAO extends GenericDAO<CommandCenter, Integer> {
  
	List<CommandCenter> findAll();
	






}


