package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Personnel;





public interface PersonnelDAO extends GenericDAO<Personnel, Integer> {
  
	List<Personnel> findAll();
	






}


