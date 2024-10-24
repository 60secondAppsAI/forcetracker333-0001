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

import com.forcetracker333.domain.Asset;
import com.forcetracker333.dto.AssetDTO;
import com.forcetracker333.dto.AssetSearchDTO;
import com.forcetracker333.dto.AssetPageDTO;
import com.forcetracker333.service.AssetService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/asset")
@RestController
public class AssetController {

	private final static Logger logger = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	AssetService assetService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Asset> getAll() {

		List<Asset> assets = assetService.findAll();
		
		return assets;	
	}

	@GetMapping(value = "/{assetId}")
	@ResponseBody
	public AssetDTO getAsset(@PathVariable Integer assetId) {
		
		return (assetService.getAssetDTOById(assetId));
	}

 	@RequestMapping(value = "/addAsset", method = RequestMethod.POST)
	public ResponseEntity<?> addAsset(@RequestBody AssetDTO assetDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assetService.addAsset(assetDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/assets")
	public ResponseEntity<AssetPageDTO> getAssets(AssetSearchDTO assetSearchDTO) {
 
		return assetService.getAssets(assetSearchDTO);
	}	

	@RequestMapping(value = "/updateAsset", method = RequestMethod.POST)
	public ResponseEntity<?> updateAsset(@RequestBody AssetDTO assetDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = assetService.updateAsset(assetDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
