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

import com.forcetracker333.domain.Soldier;
import com.forcetracker333.dto.SoldierDTO;
import com.forcetracker333.dto.SoldierSearchDTO;
import com.forcetracker333.dto.SoldierPageDTO;
import com.forcetracker333.service.SoldierService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/soldier")
@RestController
public class SoldierController {

	private final static Logger logger = LoggerFactory.getLogger(SoldierController.class);

	@Autowired
	SoldierService soldierService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Soldier> getAll() {

		List<Soldier> soldiers = soldierService.findAll();
		
		return soldiers;	
	}

	@GetMapping(value = "/{soldierId}")
	@ResponseBody
	public SoldierDTO getSoldier(@PathVariable Integer soldierId) {
		
		return (soldierService.getSoldierDTOById(soldierId));
	}

 	@RequestMapping(value = "/addSoldier", method = RequestMethod.POST)
	public ResponseEntity<?> addSoldier(@RequestBody SoldierDTO soldierDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = soldierService.addSoldier(soldierDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/soldiers")
	public ResponseEntity<SoldierPageDTO> getSoldiers(SoldierSearchDTO soldierSearchDTO) {
 
		return soldierService.getSoldiers(soldierSearchDTO);
	}	

	@RequestMapping(value = "/updateSoldier", method = RequestMethod.POST)
	public ResponseEntity<?> updateSoldier(@RequestBody SoldierDTO soldierDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = soldierService.updateSoldier(soldierDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
