package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.TacticalMap;
import com.forcetracker333.dto.TacticalMapDTO;
import com.forcetracker333.dto.TacticalMapSearchDTO;
import com.forcetracker333.dto.TacticalMapPageDTO;
import com.forcetracker333.dto.TacticalMapConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TacticalMapService extends GenericService<TacticalMap, Integer> {

	List<TacticalMap> findAll();

	ResultDTO addTacticalMap(TacticalMapDTO tacticalMapDTO, RequestDTO requestDTO);

	ResultDTO updateTacticalMap(TacticalMapDTO tacticalMapDTO, RequestDTO requestDTO);

    Page<TacticalMap> getAllTacticalMaps(Pageable pageable);

    Page<TacticalMap> getAllTacticalMaps(Specification<TacticalMap> spec, Pageable pageable);

	ResponseEntity<TacticalMapPageDTO> getTacticalMaps(TacticalMapSearchDTO tacticalMapSearchDTO);
	
	List<TacticalMapDTO> convertTacticalMapsToTacticalMapDTOs(List<TacticalMap> tacticalMaps, TacticalMapConvertCriteriaDTO convertCriteria);

	TacticalMapDTO getTacticalMapDTOById(Integer tacticalMapId);







}





