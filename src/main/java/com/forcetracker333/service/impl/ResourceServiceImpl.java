package com.forcetracker333.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.forcetracker333.dao.GenericDAO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.service.impl.GenericServiceImpl;
import com.forcetracker333.dao.ResourceDAO;
import com.forcetracker333.domain.Resource;
import com.forcetracker333.dto.ResourceDTO;
import com.forcetracker333.dto.ResourceSearchDTO;
import com.forcetracker333.dto.ResourcePageDTO;
import com.forcetracker333.dto.ResourceConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.ResourceService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class ResourceServiceImpl extends GenericServiceImpl<Resource, Integer> implements ResourceService {

    private final static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Autowired
	ResourceDAO resourceDao;

	


	@Override
	public GenericDAO<Resource, Integer> getDAO() {
		return (GenericDAO<Resource, Integer>) resourceDao;
	}
	
	public List<Resource> findAll () {
		List<Resource> resources = resourceDao.findAll();
		
		return resources;	
		
	}

	public ResultDTO addResource(ResourceDTO resourceDTO, RequestDTO requestDTO) {

		Resource resource = new Resource();

		resource.setResourceId(resourceDTO.getResourceId());


		resource.setName(resourceDTO.getName());


		resource.setQuantity(resourceDTO.getQuantity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		resource = resourceDao.save(resource);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Resource> getAllResources(Pageable pageable) {
		return resourceDao.findAll(pageable);
	}

	public Page<Resource> getAllResources(Specification<Resource> spec, Pageable pageable) {
		return resourceDao.findAll(spec, pageable);
	}

	public ResponseEntity<ResourcePageDTO> getResources(ResourceSearchDTO resourceSearchDTO) {
	
			Integer resourceId = resourceSearchDTO.getResourceId(); 
 			String name = resourceSearchDTO.getName(); 
 			Integer quantity = resourceSearchDTO.getQuantity(); 
 			String sortBy = resourceSearchDTO.getSortBy();
			String sortOrder = resourceSearchDTO.getSortOrder();
			String searchQuery = resourceSearchDTO.getSearchQuery();
			Integer page = resourceSearchDTO.getPage();
			Integer size = resourceSearchDTO.getSize();

	        Specification<Resource> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, resourceId, "resourceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, quantity, "quantity"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Resource> resources = this.getAllResources(spec, pageable);
		
		//System.out.println(String.valueOf(resources.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(resources.getTotalPages()));
		
		List<Resource> resourcesList = resources.getContent();
		
		ResourceConvertCriteriaDTO convertCriteria = new ResourceConvertCriteriaDTO();
		List<ResourceDTO> resourceDTOs = this.convertResourcesToResourceDTOs(resourcesList,convertCriteria);
		
		ResourcePageDTO resourcePageDTO = new ResourcePageDTO();
		resourcePageDTO.setResources(resourceDTOs);
		resourcePageDTO.setTotalElements(resources.getTotalElements());
		return ResponseEntity.ok(resourcePageDTO);
	}

	public List<ResourceDTO> convertResourcesToResourceDTOs(List<Resource> resources, ResourceConvertCriteriaDTO convertCriteria) {
		
		List<ResourceDTO> resourceDTOs = new ArrayList<ResourceDTO>();
		
		for (Resource resource : resources) {
			resourceDTOs.add(convertResourceToResourceDTO(resource,convertCriteria));
		}
		
		return resourceDTOs;

	}
	
	public ResourceDTO convertResourceToResourceDTO(Resource resource, ResourceConvertCriteriaDTO convertCriteria) {
		
		ResourceDTO resourceDTO = new ResourceDTO();
		
		resourceDTO.setResourceId(resource.getResourceId());

	
		resourceDTO.setName(resource.getName());

	
		resourceDTO.setQuantity(resource.getQuantity());

	

		
		return resourceDTO;
	}

	public ResultDTO updateResource(ResourceDTO resourceDTO, RequestDTO requestDTO) {
		
		Resource resource = resourceDao.getById(resourceDTO.getResourceId());

		resource.setResourceId(ControllerUtils.setValue(resource.getResourceId(), resourceDTO.getResourceId()));

		resource.setName(ControllerUtils.setValue(resource.getName(), resourceDTO.getName()));

		resource.setQuantity(ControllerUtils.setValue(resource.getQuantity(), resourceDTO.getQuantity()));



        resource = resourceDao.save(resource);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ResourceDTO getResourceDTOById(Integer resourceId) {
	
		Resource resource = resourceDao.getById(resourceId);
			
		
		ResourceConvertCriteriaDTO convertCriteria = new ResourceConvertCriteriaDTO();
		return(this.convertResourceToResourceDTO(resource,convertCriteria));
	}







}
