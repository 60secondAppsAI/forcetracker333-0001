package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Resource;





public interface ResourceDAO extends GenericDAO<Resource, Integer> {
  
	List<Resource> findAll();
	






}


