package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Fleet;
import com.forcetracker333.dto.FleetDTO;
import com.forcetracker333.dto.FleetSearchDTO;
import com.forcetracker333.dto.FleetPageDTO;
import com.forcetracker333.dto.FleetConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FleetService extends GenericService<Fleet, Integer> {

	List<Fleet> findAll();

	ResultDTO addFleet(FleetDTO fleetDTO, RequestDTO requestDTO);

	ResultDTO updateFleet(FleetDTO fleetDTO, RequestDTO requestDTO);

    Page<Fleet> getAllFleets(Pageable pageable);

    Page<Fleet> getAllFleets(Specification<Fleet> spec, Pageable pageable);

	ResponseEntity<FleetPageDTO> getFleets(FleetSearchDTO fleetSearchDTO);
	
	List<FleetDTO> convertFleetsToFleetDTOs(List<Fleet> fleets, FleetConvertCriteriaDTO convertCriteria);

	FleetDTO getFleetDTOById(Integer fleetId);







}





