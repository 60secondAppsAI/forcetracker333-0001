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

import com.forcetracker333.domain.Resource;
import com.forcetracker333.dto.ResourceDTO;
import com.forcetracker333.dto.ResourceSearchDTO;
import com.forcetracker333.dto.ResourcePageDTO;
import com.forcetracker333.service.ResourceService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/resource")
@RestController
public class ResourceController {

	private final static Logger logger = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	ResourceService resourceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Resource> getAll() {

		List<Resource> resources = resourceService.findAll();
		
		return resources;	
	}

	@GetMapping(value = "/{resourceId}")
	@ResponseBody
	public ResourceDTO getResource(@PathVariable Integer resourceId) {
		
		return (resourceService.getResourceDTOById(resourceId));
	}

 	@RequestMapping(value = "/addResource", method = RequestMethod.POST)
	public ResponseEntity<?> addResource(@RequestBody ResourceDTO resourceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = resourceService.addResource(resourceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/resources")
	public ResponseEntity<ResourcePageDTO> getResources(ResourceSearchDTO resourceSearchDTO) {
 
		return resourceService.getResources(resourceSearchDTO);
	}	

	@RequestMapping(value = "/updateResource", method = RequestMethod.POST)
	public ResponseEntity<?> updateResource(@RequestBody ResourceDTO resourceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = resourceService.updateResource(resourceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
