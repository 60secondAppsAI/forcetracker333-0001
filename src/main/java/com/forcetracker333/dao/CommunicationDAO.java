package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Communication;





public interface CommunicationDAO extends GenericDAO<Communication, Integer> {
  
	List<Communication> findAll();
	






}


