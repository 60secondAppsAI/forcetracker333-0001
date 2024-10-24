package com.forcetracker333.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.forcetracker333.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.forcetracker333.domain.Operation;
import com.forcetracker333.dto.OperationDTO;
import com.forcetracker333.dto.OperationSearchDTO;
import com.forcetracker333.dto.OperationPageDTO;
import com.forcetracker333.service.OperationService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/operation")
@RestController
public class OperationController {

	private final static Logger logger = LoggerFactory.getLogger(OperationController.class);

	@Autowired
	OperationService operationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Operation> getAll() {

		List<Operation> operations = operationService.findAll();
		
		return operations;	
	}

	@GetMapping(value = "/{operationId}")
	@ResponseBody
	public OperationDTO getOperation(@PathVariable Integer operationId) {
		
		return (operationService.getOperationDTOById(operationId));
	}

 	@RequestMapping(value = "/addOperation", method = RequestMethod.POST)
	public ResponseEntity<?> addOperation(@RequestBody OperationDTO operationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = operationService.addOperation(operationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/operations")
	public ResponseEntity<OperationPageDTO> getOperations(OperationSearchDTO operationSearchDTO) {
 
		return operationService.getOperations(operationSearchDTO);
	}	

	@RequestMapping(value = "/updateOperation", method = RequestMethod.POST)
	public ResponseEntity<?> updateOperation(@RequestBody OperationDTO operationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = operationService.updateOperation(operationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
