package com.forcetracker333.dao;

import java.util.List;

import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.domain.Asset;





public interface AssetDAO extends GenericDAO<Asset, Integer> {
  
	List<Asset> findAll();
	






}


