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

import com.forcetracker333.domain.RiskAssessment;
import com.forcetracker333.dto.RiskAssessmentDTO;
import com.forcetracker333.dto.RiskAssessmentSearchDTO;
import com.forcetracker333.dto.RiskAssessmentPageDTO;
import com.forcetracker333.service.RiskAssessmentService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/riskAssessment")
@RestController
public class RiskAssessmentController {

	private final static Logger logger = LoggerFactory.getLogger(RiskAssessmentController.class);

	@Autowired
	RiskAssessmentService riskAssessmentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RiskAssessment> getAll() {

		List<RiskAssessment> riskAssessments = riskAssessmentService.findAll();
		
		return riskAssessments;	
	}

	@GetMapping(value = "/{riskAssessmentId}")
	@ResponseBody
	public RiskAssessmentDTO getRiskAssessment(@PathVariable Integer riskAssessmentId) {
		
		return (riskAssessmentService.getRiskAssessmentDTOById(riskAssessmentId));
	}

 	@RequestMapping(value = "/addRiskAssessment", method = RequestMethod.POST)
	public ResponseEntity<?> addRiskAssessment(@RequestBody RiskAssessmentDTO riskAssessmentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = riskAssessmentService.addRiskAssessment(riskAssessmentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/riskAssessments")
	public ResponseEntity<RiskAssessmentPageDTO> getRiskAssessments(RiskAssessmentSearchDTO riskAssessmentSearchDTO) {
 
		return riskAssessmentService.getRiskAssessments(riskAssessmentSearchDTO);
	}	

	@RequestMapping(value = "/updateRiskAssessment", method = RequestMethod.POST)
	public ResponseEntity<?> updateRiskAssessment(@RequestBody RiskAssessmentDTO riskAssessmentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = riskAssessmentService.updateRiskAssessment(riskAssessmentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
