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

import com.forcetracker333.domain.Country;
import com.forcetracker333.dto.CountryDTO;
import com.forcetracker333.dto.CountrySearchDTO;
import com.forcetracker333.dto.CountryPageDTO;
import com.forcetracker333.service.CountryService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/country")
@RestController
public class CountryController {

	private final static Logger logger = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	CountryService countryService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Country> getAll() {

		List<Country> countrys = countryService.findAll();
		
		return countrys;	
	}

	@GetMapping(value = "/{countryId}")
	@ResponseBody
	public CountryDTO getCountry(@PathVariable Integer countryId) {
		
		return (countryService.getCountryDTOById(countryId));
	}

 	@RequestMapping(value = "/addCountry", method = RequestMethod.POST)
	public ResponseEntity<?> addCountry(@RequestBody CountryDTO countryDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = countryService.addCountry(countryDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/countrys")
	public ResponseEntity<CountryPageDTO> getCountrys(CountrySearchDTO countrySearchDTO) {
 
		return countryService.getCountrys(countrySearchDTO);
	}	

	@RequestMapping(value = "/updateCountry", method = RequestMethod.POST)
	public ResponseEntity<?> updateCountry(@RequestBody CountryDTO countryDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = countryService.updateCountry(countryDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
