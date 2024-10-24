package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Resource;
import com.forcetracker333.dto.ResourceDTO;
import com.forcetracker333.dto.ResourceSearchDTO;
import com.forcetracker333.dto.ResourcePageDTO;
import com.forcetracker333.dto.ResourceConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ResourceService extends GenericService<Resource, Integer> {

	List<Resource> findAll();

	ResultDTO addResource(ResourceDTO resourceDTO, RequestDTO requestDTO);

	ResultDTO updateResource(ResourceDTO resourceDTO, RequestDTO requestDTO);

    Page<Resource> getAllResources(Pageable pageable);

    Page<Resource> getAllResources(Specification<Resource> spec, Pageable pageable);

	ResponseEntity<ResourcePageDTO> getResources(ResourceSearchDTO resourceSearchDTO);
	
	List<ResourceDTO> convertResourcesToResourceDTOs(List<Resource> resources, ResourceConvertCriteriaDTO convertCriteria);

	ResourceDTO getResourceDTOById(Integer resourceId);







}





