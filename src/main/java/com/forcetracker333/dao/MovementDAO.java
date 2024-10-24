package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Movement;





public interface MovementDAO extends GenericDAO<Movement, Integer> {
  
	List<Movement> findAll();
	






}


