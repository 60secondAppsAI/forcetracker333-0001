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

import com.forcetracker333.domain.IntelligenceReport;
import com.forcetracker333.dto.IntelligenceReportDTO;
import com.forcetracker333.dto.IntelligenceReportSearchDTO;
import com.forcetracker333.dto.IntelligenceReportPageDTO;
import com.forcetracker333.service.IntelligenceReportService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/intelligenceReport")
@RestController
public class IntelligenceReportController {

	private final static Logger logger = LoggerFactory.getLogger(IntelligenceReportController.class);

	@Autowired
	IntelligenceReportService intelligenceReportService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<IntelligenceReport> getAll() {

		List<IntelligenceReport> intelligenceReports = intelligenceReportService.findAll();
		
		return intelligenceReports;	
	}

	@GetMapping(value = "/{intelligenceReportId}")
	@ResponseBody
	public IntelligenceReportDTO getIntelligenceReport(@PathVariable Integer intelligenceReportId) {
		
		return (intelligenceReportService.getIntelligenceReportDTOById(intelligenceReportId));
	}

 	@RequestMapping(value = "/addIntelligenceReport", method = RequestMethod.POST)
	public ResponseEntity<?> addIntelligenceReport(@RequestBody IntelligenceReportDTO intelligenceReportDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = intelligenceReportService.addIntelligenceReport(intelligenceReportDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/intelligenceReports")
	public ResponseEntity<IntelligenceReportPageDTO> getIntelligenceReports(IntelligenceReportSearchDTO intelligenceReportSearchDTO) {
 
		return intelligenceReportService.getIntelligenceReports(intelligenceReportSearchDTO);
	}	

	@RequestMapping(value = "/updateIntelligenceReport", method = RequestMethod.POST)
	public ResponseEntity<?> updateIntelligenceReport(@RequestBody IntelligenceReportDTO intelligenceReportDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = intelligenceReportService.updateIntelligenceReport(intelligenceReportDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
