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

import com.forcetracker333.domain.TacticalMap;
import com.forcetracker333.dto.TacticalMapDTO;
import com.forcetracker333.dto.TacticalMapSearchDTO;
import com.forcetracker333.dto.TacticalMapPageDTO;
import com.forcetracker333.service.TacticalMapService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/tacticalMap")
@RestController
public class TacticalMapController {

	private final static Logger logger = LoggerFactory.getLogger(TacticalMapController.class);

	@Autowired
	TacticalMapService tacticalMapService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TacticalMap> getAll() {

		List<TacticalMap> tacticalMaps = tacticalMapService.findAll();
		
		return tacticalMaps;	
	}

	@GetMapping(value = "/{tacticalMapId}")
	@ResponseBody
	public TacticalMapDTO getTacticalMap(@PathVariable Integer tacticalMapId) {
		
		return (tacticalMapService.getTacticalMapDTOById(tacticalMapId));
	}

 	@RequestMapping(value = "/addTacticalMap", method = RequestMethod.POST)
	public ResponseEntity<?> addTacticalMap(@RequestBody TacticalMapDTO tacticalMapDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = tacticalMapService.addTacticalMap(tacticalMapDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/tacticalMaps")
	public ResponseEntity<TacticalMapPageDTO> getTacticalMaps(TacticalMapSearchDTO tacticalMapSearchDTO) {
 
		return tacticalMapService.getTacticalMaps(tacticalMapSearchDTO);
	}	

	@RequestMapping(value = "/updateTacticalMap", method = RequestMethod.POST)
	public ResponseEntity<?> updateTacticalMap(@RequestBody TacticalMapDTO tacticalMapDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = tacticalMapService.updateTacticalMap(tacticalMapDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
