package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.CommandCenter;
import com.forcetracker333.dto.CommandCenterDTO;
import com.forcetracker333.dto.CommandCenterSearchDTO;
import com.forcetracker333.dto.CommandCenterPageDTO;
import com.forcetracker333.dto.CommandCenterConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CommandCenterService extends GenericService<CommandCenter, Integer> {

	List<CommandCenter> findAll();

	ResultDTO addCommandCenter(CommandCenterDTO commandCenterDTO, RequestDTO requestDTO);

	ResultDTO updateCommandCenter(CommandCenterDTO commandCenterDTO, RequestDTO requestDTO);

    Page<CommandCenter> getAllCommandCenters(Pageable pageable);

    Page<CommandCenter> getAllCommandCenters(Specification<CommandCenter> spec, Pageable pageable);

	ResponseEntity<CommandCenterPageDTO> getCommandCenters(CommandCenterSearchDTO commandCenterSearchDTO);
	
	List<CommandCenterDTO> convertCommandCentersToCommandCenterDTOs(List<CommandCenter> commandCenters, CommandCenterConvertCriteriaDTO convertCriteria);

	CommandCenterDTO getCommandCenterDTOById(Integer commandCenterId);







}





