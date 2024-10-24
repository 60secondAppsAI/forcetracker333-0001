package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.MilitaryUnit;





public interface MilitaryUnitDAO extends GenericDAO<MilitaryUnit, Integer> {
  
	List<MilitaryUnit> findAll();
	






}


