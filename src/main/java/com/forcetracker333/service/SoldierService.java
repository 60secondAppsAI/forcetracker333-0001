package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Soldier;
import com.forcetracker333.dto.SoldierDTO;
import com.forcetracker333.dto.SoldierSearchDTO;
import com.forcetracker333.dto.SoldierPageDTO;
import com.forcetracker333.dto.SoldierConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SoldierService extends GenericService<Soldier, Integer> {

	List<Soldier> findAll();

	ResultDTO addSoldier(SoldierDTO soldierDTO, RequestDTO requestDTO);

	ResultDTO updateSoldier(SoldierDTO soldierDTO, RequestDTO requestDTO);

    Page<Soldier> getAllSoldiers(Pageable pageable);

    Page<Soldier> getAllSoldiers(Specification<Soldier> spec, Pageable pageable);

	ResponseEntity<SoldierPageDTO> getSoldiers(SoldierSearchDTO soldierSearchDTO);
	
	List<SoldierDTO> convertSoldiersToSoldierDTOs(List<Soldier> soldiers, SoldierConvertCriteriaDTO convertCriteria);

	SoldierDTO getSoldierDTOById(Integer soldierId);







}





