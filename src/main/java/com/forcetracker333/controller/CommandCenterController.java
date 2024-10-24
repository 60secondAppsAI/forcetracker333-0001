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

import com.forcetracker333.domain.CommandCenter;
import com.forcetracker333.dto.CommandCenterDTO;
import com.forcetracker333.dto.CommandCenterSearchDTO;
import com.forcetracker333.dto.CommandCenterPageDTO;
import com.forcetracker333.service.CommandCenterService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/commandCenter")
@RestController
public class CommandCenterController {

	private final static Logger logger = LoggerFactory.getLogger(CommandCenterController.class);

	@Autowired
	CommandCenterService commandCenterService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CommandCenter> getAll() {

		List<CommandCenter> commandCenters = commandCenterService.findAll();
		
		return commandCenters;	
	}

	@GetMapping(value = "/{commandCenterId}")
	@ResponseBody
	public CommandCenterDTO getCommandCenter(@PathVariable Integer commandCenterId) {
		
		return (commandCenterService.getCommandCenterDTOById(commandCenterId));
	}

 	@RequestMapping(value = "/addCommandCenter", method = RequestMethod.POST)
	public ResponseEntity<?> addCommandCenter(@RequestBody CommandCenterDTO commandCenterDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commandCenterService.addCommandCenter(commandCenterDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/commandCenters")
	public ResponseEntity<CommandCenterPageDTO> getCommandCenters(CommandCenterSearchDTO commandCenterSearchDTO) {
 
		return commandCenterService.getCommandCenters(commandCenterSearchDTO);
	}	

	@RequestMapping(value = "/updateCommandCenter", method = RequestMethod.POST)
	public ResponseEntity<?> updateCommandCenter(@RequestBody CommandCenterDTO commandCenterDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = commandCenterService.updateCommandCenter(commandCenterDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
