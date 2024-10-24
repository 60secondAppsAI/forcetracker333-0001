package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.MilitaryUnit;
import com.forcetracker333.dto.MilitaryUnitDTO;
import com.forcetracker333.dto.MilitaryUnitSearchDTO;
import com.forcetracker333.dto.MilitaryUnitPageDTO;
import com.forcetracker333.dto.MilitaryUnitConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MilitaryUnitService extends GenericService<MilitaryUnit, Integer> {

	List<MilitaryUnit> findAll();

	ResultDTO addMilitaryUnit(MilitaryUnitDTO militaryUnitDTO, RequestDTO requestDTO);

	ResultDTO updateMilitaryUnit(MilitaryUnitDTO militaryUnitDTO, RequestDTO requestDTO);

    Page<MilitaryUnit> getAllMilitaryUnits(Pageable pageable);

    Page<MilitaryUnit> getAllMilitaryUnits(Specification<MilitaryUnit> spec, Pageable pageable);

	ResponseEntity<MilitaryUnitPageDTO> getMilitaryUnits(MilitaryUnitSearchDTO militaryUnitSearchDTO);
	
	List<MilitaryUnitDTO> convertMilitaryUnitsToMilitaryUnitDTOs(List<MilitaryUnit> militaryUnits, MilitaryUnitConvertCriteriaDTO convertCriteria);

	MilitaryUnitDTO getMilitaryUnitDTOById(Integer militaryUnitId);







}





