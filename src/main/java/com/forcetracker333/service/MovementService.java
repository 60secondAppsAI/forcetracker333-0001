package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Movement;
import com.forcetracker333.dto.MovementDTO;
import com.forcetracker333.dto.MovementSearchDTO;
import com.forcetracker333.dto.MovementPageDTO;
import com.forcetracker333.dto.MovementConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MovementService extends GenericService<Movement, Integer> {

	List<Movement> findAll();

	ResultDTO addMovement(MovementDTO movementDTO, RequestDTO requestDTO);

	ResultDTO updateMovement(MovementDTO movementDTO, RequestDTO requestDTO);

    Page<Movement> getAllMovements(Pageable pageable);

    Page<Movement> getAllMovements(Specification<Movement> spec, Pageable pageable);

	ResponseEntity<MovementPageDTO> getMovements(MovementSearchDTO movementSearchDTO);
	
	List<MovementDTO> convertMovementsToMovementDTOs(List<Movement> movements, MovementConvertCriteriaDTO convertCriteria);

	MovementDTO getMovementDTOById(Integer movementId);







}





