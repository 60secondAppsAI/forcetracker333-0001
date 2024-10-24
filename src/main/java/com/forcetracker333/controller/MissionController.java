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

import com.forcetracker333.domain.Mission;
import com.forcetracker333.dto.MissionDTO;
import com.forcetracker333.dto.MissionSearchDTO;
import com.forcetracker333.dto.MissionPageDTO;
import com.forcetracker333.service.MissionService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/mission")
@RestController
public class MissionController {

	private final static Logger logger = LoggerFactory.getLogger(MissionController.class);

	@Autowired
	MissionService missionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Mission> getAll() {

		List<Mission> missions = missionService.findAll();
		
		return missions;	
	}

	@GetMapping(value = "/{missionId}")
	@ResponseBody
	public MissionDTO getMission(@PathVariable Integer missionId) {
		
		return (missionService.getMissionDTOById(missionId));
	}

 	@RequestMapping(value = "/addMission", method = RequestMethod.POST)
	public ResponseEntity<?> addMission(@RequestBody MissionDTO missionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = missionService.addMission(missionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/missions")
	public ResponseEntity<MissionPageDTO> getMissions(MissionSearchDTO missionSearchDTO) {
 
		return missionService.getMissions(missionSearchDTO);
	}	

	@RequestMapping(value = "/updateMission", method = RequestMethod.POST)
	public ResponseEntity<?> updateMission(@RequestBody MissionDTO missionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = missionService.updateMission(missionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
