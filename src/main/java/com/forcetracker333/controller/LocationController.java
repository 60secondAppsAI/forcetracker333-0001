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

import com.forcetracker333.domain.Location;
import com.forcetracker333.dto.LocationDTO;
import com.forcetracker333.dto.LocationSearchDTO;
import com.forcetracker333.dto.LocationPageDTO;
import com.forcetracker333.service.LocationService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/location")
@RestController
public class LocationController {

	private final static Logger logger = LoggerFactory.getLogger(LocationController.class);

	@Autowired
	LocationService locationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Location> getAll() {

		List<Location> locations = locationService.findAll();
		
		return locations;	
	}

	@GetMapping(value = "/{locationId}")
	@ResponseBody
	public LocationDTO getLocation(@PathVariable Integer locationId) {
		
		return (locationService.getLocationDTOById(locationId));
	}

 	@RequestMapping(value = "/addLocation", method = RequestMethod.POST)
	public ResponseEntity<?> addLocation(@RequestBody LocationDTO locationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = locationService.addLocation(locationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/locations")
	public ResponseEntity<LocationPageDTO> getLocations(LocationSearchDTO locationSearchDTO) {
 
		return locationService.getLocations(locationSearchDTO);
	}	

	@RequestMapping(value = "/updateLocation", method = RequestMethod.POST)
	public ResponseEntity<?> updateLocation(@RequestBody LocationDTO locationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = locationService.updateLocation(locationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
