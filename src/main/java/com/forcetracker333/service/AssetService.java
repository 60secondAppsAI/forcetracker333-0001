package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Asset;
import com.forcetracker333.dto.AssetDTO;
import com.forcetracker333.dto.AssetSearchDTO;
import com.forcetracker333.dto.AssetPageDTO;
import com.forcetracker333.dto.AssetConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AssetService extends GenericService<Asset, Integer> {

	List<Asset> findAll();

	ResultDTO addAsset(AssetDTO assetDTO, RequestDTO requestDTO);

	ResultDTO updateAsset(AssetDTO assetDTO, RequestDTO requestDTO);

    Page<Asset> getAllAssets(Pageable pageable);

    Page<Asset> getAllAssets(Specification<Asset> spec, Pageable pageable);

	ResponseEntity<AssetPageDTO> getAssets(AssetSearchDTO assetSearchDTO);
	
	List<AssetDTO> convertAssetsToAssetDTOs(List<Asset> assets, AssetConvertCriteriaDTO convertCriteria);

	AssetDTO getAssetDTOById(Integer assetId);







}





