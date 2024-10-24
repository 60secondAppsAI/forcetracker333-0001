package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Soldier;





public interface SoldierDAO extends GenericDAO<Soldier, Integer> {
  
	List<Soldier> findAll();
	






}


