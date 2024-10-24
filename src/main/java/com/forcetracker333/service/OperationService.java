package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Operation;
import com.forcetracker333.dto.OperationDTO;
import com.forcetracker333.dto.OperationSearchDTO;
import com.forcetracker333.dto.OperationPageDTO;
import com.forcetracker333.dto.OperationConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OperationService extends GenericService<Operation, Integer> {

	List<Operation> findAll();

	ResultDTO addOperation(OperationDTO operationDTO, RequestDTO requestDTO);

	ResultDTO updateOperation(OperationDTO operationDTO, RequestDTO requestDTO);

    Page<Operation> getAllOperations(Pageable pageable);

    Page<Operation> getAllOperations(Specification<Operation> spec, Pageable pageable);

	ResponseEntity<OperationPageDTO> getOperations(OperationSearchDTO operationSearchDTO);
	
	List<OperationDTO> convertOperationsToOperationDTOs(List<Operation> operations, OperationConvertCriteriaDTO convertCriteria);

	OperationDTO getOperationDTOById(Integer operationId);







}





