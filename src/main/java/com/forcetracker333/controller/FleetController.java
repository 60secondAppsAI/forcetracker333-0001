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

import com.forcetracker333.domain.Fleet;
import com.forcetracker333.dto.FleetDTO;
import com.forcetracker333.dto.FleetSearchDTO;
import com.forcetracker333.dto.FleetPageDTO;
import com.forcetracker333.service.FleetService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/fleet")
@RestController
public class FleetController {

	private final static Logger logger = LoggerFactory.getLogger(FleetController.class);

	@Autowired
	FleetService fleetService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Fleet> getAll() {

		List<Fleet> fleets = fleetService.findAll();
		
		return fleets;	
	}

	@GetMapping(value = "/{fleetId}")
	@ResponseBody
	public FleetDTO getFleet(@PathVariable Integer fleetId) {
		
		return (fleetService.getFleetDTOById(fleetId));
	}

 	@RequestMapping(value = "/addFleet", method = RequestMethod.POST)
	public ResponseEntity<?> addFleet(@RequestBody FleetDTO fleetDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = fleetService.addFleet(fleetDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/fleets")
	public ResponseEntity<FleetPageDTO> getFleets(FleetSearchDTO fleetSearchDTO) {
 
		return fleetService.getFleets(fleetSearchDTO);
	}	

	@RequestMapping(value = "/updateFleet", method = RequestMethod.POST)
	public ResponseEntity<?> updateFleet(@RequestBody FleetDTO fleetDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = fleetService.updateFleet(fleetDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
