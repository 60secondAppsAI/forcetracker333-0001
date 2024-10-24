package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Operation;





public interface OperationDAO extends GenericDAO<Operation, Integer> {
  
	List<Operation> findAll();
	






}


