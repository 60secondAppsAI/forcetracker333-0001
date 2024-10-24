package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Country;





public interface CountryDAO extends GenericDAO<Country, Integer> {
  
	List<Country> findAll();
	






}


