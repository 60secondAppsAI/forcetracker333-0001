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

import com.forcetracker333.domain.Surveillance;
import com.forcetracker333.dto.SurveillanceDTO;
import com.forcetracker333.dto.SurveillanceSearchDTO;
import com.forcetracker333.dto.SurveillancePageDTO;
import com.forcetracker333.service.SurveillanceService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/surveillance")
@RestController
public class SurveillanceController {

	private final static Logger logger = LoggerFactory.getLogger(SurveillanceController.class);

	@Autowired
	SurveillanceService surveillanceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Surveillance> getAll() {

		List<Surveillance> surveillances = surveillanceService.findAll();
		
		return surveillances;	
	}

	@GetMapping(value = "/{surveillanceId}")
	@ResponseBody
	public SurveillanceDTO getSurveillance(@PathVariable Integer surveillanceId) {
		
		return (surveillanceService.getSurveillanceDTOById(surveillanceId));
	}

 	@RequestMapping(value = "/addSurveillance", method = RequestMethod.POST)
	public ResponseEntity<?> addSurveillance(@RequestBody SurveillanceDTO surveillanceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceService.addSurveillance(surveillanceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/surveillances")
	public ResponseEntity<SurveillancePageDTO> getSurveillances(SurveillanceSearchDTO surveillanceSearchDTO) {
 
		return surveillanceService.getSurveillances(surveillanceSearchDTO);
	}	

	@RequestMapping(value = "/updateSurveillance", method = RequestMethod.POST)
	public ResponseEntity<?> updateSurveillance(@RequestBody SurveillanceDTO surveillanceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = surveillanceService.updateSurveillance(surveillanceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
