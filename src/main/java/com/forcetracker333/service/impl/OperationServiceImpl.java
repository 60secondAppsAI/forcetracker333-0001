package com.forcetracker333.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.service.impl.GenericServiceImpl;
import com.forcetracker333.dao.OperationDAO;
import com.forcetracker333.domain.Operation;
import com.forcetracker333.dto.OperationDTO;
import com.forcetracker333.dto.OperationSearchDTO;
import com.forcetracker333.dto.OperationPageDTO;
import com.forcetracker333.dto.OperationConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.OperationService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class OperationServiceImpl extends GenericServiceImpl<Operation, Integer> implements OperationService {

    private final static Logger logger = LoggerFactory.getLogger(OperationServiceImpl.class);

	@Autowired
	OperationDAO operationDao;

	


	@Override
	public GenericDAO<Operation, Integer> getDAO() {
		return (GenericDAO<Operation, Integer>) operationDao;
	}
	
	public List<Operation> findAll () {
		List<Operation> operations = operationDao.findAll();
		
		return operations;	
		
	}

	public ResultDTO addOperation(OperationDTO operationDTO, RequestDTO requestDTO) {

		Operation operation = new Operation();

		operation.setOperationId(operationDTO.getOperationId());


		operation.setName(operationDTO.getName());


		operation.setDescription(operationDTO.getDescription());


		operation.setStartDate(operationDTO.getStartDate());


		operation.setEndDate(operationDTO.getEndDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		operation = operationDao.save(operation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Operation> getAllOperations(Pageable pageable) {
		return operationDao.findAll(pageable);
	}

	public Page<Operation> getAllOperations(Specification<Operation> spec, Pageable pageable) {
		return operationDao.findAll(spec, pageable);
	}

	public ResponseEntity<OperationPageDTO> getOperations(OperationSearchDTO operationSearchDTO) {
	
			Integer operationId = operationSearchDTO.getOperationId(); 
 			String name = operationSearchDTO.getName(); 
 			String description = operationSearchDTO.getDescription(); 
     			String sortBy = operationSearchDTO.getSortBy();
			String sortOrder = operationSearchDTO.getSortOrder();
			String searchQuery = operationSearchDTO.getSearchQuery();
			Integer page = operationSearchDTO.getPage();
			Integer size = operationSearchDTO.getSize();

	        Specification<Operation> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, operationId, "operationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Operation> operations = this.getAllOperations(spec, pageable);
		
		//System.out.println(String.valueOf(operations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(operations.getTotalPages()));
		
		List<Operation> operationsList = operations.getContent();
		
		OperationConvertCriteriaDTO convertCriteria = new OperationConvertCriteriaDTO();
		List<OperationDTO> operationDTOs = this.convertOperationsToOperationDTOs(operationsList,convertCriteria);
		
		OperationPageDTO operationPageDTO = new OperationPageDTO();
		operationPageDTO.setOperations(operationDTOs);
		operationPageDTO.setTotalElements(operations.getTotalElements());
		return ResponseEntity.ok(operationPageDTO);
	}

	public List<OperationDTO> convertOperationsToOperationDTOs(List<Operation> operations, OperationConvertCriteriaDTO convertCriteria) {
		
		List<OperationDTO> operationDTOs = new ArrayList<OperationDTO>();
		
		for (Operation operation : operations) {
			operationDTOs.add(convertOperationToOperationDTO(operation,convertCriteria));
		}
		
		return operationDTOs;

	}
	
	public OperationDTO convertOperationToOperationDTO(Operation operation, OperationConvertCriteriaDTO convertCriteria) {
		
		OperationDTO operationDTO = new OperationDTO();
		
		operationDTO.setOperationId(operation.getOperationId());

	
		operationDTO.setName(operation.getName());

	
		operationDTO.setDescription(operation.getDescription());

	
		operationDTO.setStartDate(operation.getStartDate());

	
		operationDTO.setEndDate(operation.getEndDate());

	

		
		return operationDTO;
	}

	public ResultDTO updateOperation(OperationDTO operationDTO, RequestDTO requestDTO) {
		
		Operation operation = operationDao.getById(operationDTO.getOperationId());

		operation.setOperationId(ControllerUtils.setValue(operation.getOperationId(), operationDTO.getOperationId()));

		operation.setName(ControllerUtils.setValue(operation.getName(), operationDTO.getName()));

		operation.setDescription(ControllerUtils.setValue(operation.getDescription(), operationDTO.getDescription()));

		operation.setStartDate(ControllerUtils.setValue(operation.getStartDate(), operationDTO.getStartDate()));

		operation.setEndDate(ControllerUtils.setValue(operation.getEndDate(), operationDTO.getEndDate()));



        operation = operationDao.save(operation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public OperationDTO getOperationDTOById(Integer operationId) {
	
		Operation operation = operationDao.getById(operationId);
			
		
		OperationConvertCriteriaDTO convertCriteria = new OperationConvertCriteriaDTO();
		return(this.convertOperationToOperationDTO(operation,convertCriteria));
	}







}
