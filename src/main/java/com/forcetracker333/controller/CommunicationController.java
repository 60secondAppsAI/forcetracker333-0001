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

import com.forcetracker333.domain.Communication;
import com.forcetracker333.dto.CommunicationDTO;
import com.forcetracker333.dto.CommunicationSearchDTO;
import com.forcetracker333.dto.CommunicationPageDTO;
import com.forcetracker333.service.CommunicationService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/communication")
@RestController
public class CommunicationController {

	private final static Logger logger = LoggerFactory.getLogger(CommunicationController.class);

	@Autowired
	CommunicationService communicationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Communication> getAll() {

		List<Communication> communications = communicationService.findAll();
		
		return communications;	
	}

	@GetMapping(value = "/{communicationId}")
	@ResponseBody
	public CommunicationDTO getCommunication(@PathVariable Integer communicationId) {
		
		return (communicationService.getCommunicationDTOById(communicationId));
	}

 	@RequestMapping(value = "/addCommunication", method = RequestMethod.POST)
	public ResponseEntity<?> addCommunication(@RequestBody CommunicationDTO communicationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = communicationService.addCommunication(communicationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/communications")
	public ResponseEntity<CommunicationPageDTO> getCommunications(CommunicationSearchDTO communicationSearchDTO) {
 
		return communicationService.getCommunications(communicationSearchDTO);
	}	

	@RequestMapping(value = "/updateCommunication", method = RequestMethod.POST)
	public ResponseEntity<?> updateCommunication(@RequestBody CommunicationDTO communicationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = communicationService.updateCommunication(communicationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
