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
import com.forcetracker333.dao.TacticalMapDAO;
import com.forcetracker333.domain.TacticalMap;
import com.forcetracker333.dto.TacticalMapDTO;
import com.forcetracker333.dto.TacticalMapSearchDTO;
import com.forcetracker333.dto.TacticalMapPageDTO;
import com.forcetracker333.dto.TacticalMapConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.TacticalMapService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class TacticalMapServiceImpl extends GenericServiceImpl<TacticalMap, Integer> implements TacticalMapService {

    private final static Logger logger = LoggerFactory.getLogger(TacticalMapServiceImpl.class);

	@Autowired
	TacticalMapDAO tacticalMapDao;

	


	@Override
	public GenericDAO<TacticalMap, Integer> getDAO() {
		return (GenericDAO<TacticalMap, Integer>) tacticalMapDao;
	}
	
	public List<TacticalMap> findAll () {
		List<TacticalMap> tacticalMaps = tacticalMapDao.findAll();
		
		return tacticalMaps;	
		
	}

	public ResultDTO addTacticalMap(TacticalMapDTO tacticalMapDTO, RequestDTO requestDTO) {

		TacticalMap tacticalMap = new TacticalMap();

		tacticalMap.setTacticalMapId(tacticalMapDTO.getTacticalMapId());


		tacticalMap.setRegion(tacticalMapDTO.getRegion());


		tacticalMap.setDateUpdated(tacticalMapDTO.getDateUpdated());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		tacticalMap = tacticalMapDao.save(tacticalMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TacticalMap> getAllTacticalMaps(Pageable pageable) {
		return tacticalMapDao.findAll(pageable);
	}

	public Page<TacticalMap> getAllTacticalMaps(Specification<TacticalMap> spec, Pageable pageable) {
		return tacticalMapDao.findAll(spec, pageable);
	}

	public ResponseEntity<TacticalMapPageDTO> getTacticalMaps(TacticalMapSearchDTO tacticalMapSearchDTO) {
	
			Integer tacticalMapId = tacticalMapSearchDTO.getTacticalMapId(); 
 			String region = tacticalMapSearchDTO.getRegion(); 
   			String sortBy = tacticalMapSearchDTO.getSortBy();
			String sortOrder = tacticalMapSearchDTO.getSortOrder();
			String searchQuery = tacticalMapSearchDTO.getSearchQuery();
			Integer page = tacticalMapSearchDTO.getPage();
			Integer size = tacticalMapSearchDTO.getSize();

	        Specification<TacticalMap> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, tacticalMapId, "tacticalMapId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, region, "region"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("region")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<TacticalMap> tacticalMaps = this.getAllTacticalMaps(spec, pageable);
		
		//System.out.println(String.valueOf(tacticalMaps.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(tacticalMaps.getTotalPages()));
		
		List<TacticalMap> tacticalMapsList = tacticalMaps.getContent();
		
		TacticalMapConvertCriteriaDTO convertCriteria = new TacticalMapConvertCriteriaDTO();
		List<TacticalMapDTO> tacticalMapDTOs = this.convertTacticalMapsToTacticalMapDTOs(tacticalMapsList,convertCriteria);
		
		TacticalMapPageDTO tacticalMapPageDTO = new TacticalMapPageDTO();
		tacticalMapPageDTO.setTacticalMaps(tacticalMapDTOs);
		tacticalMapPageDTO.setTotalElements(tacticalMaps.getTotalElements());
		return ResponseEntity.ok(tacticalMapPageDTO);
	}

	public List<TacticalMapDTO> convertTacticalMapsToTacticalMapDTOs(List<TacticalMap> tacticalMaps, TacticalMapConvertCriteriaDTO convertCriteria) {
		
		List<TacticalMapDTO> tacticalMapDTOs = new ArrayList<TacticalMapDTO>();
		
		for (TacticalMap tacticalMap : tacticalMaps) {
			tacticalMapDTOs.add(convertTacticalMapToTacticalMapDTO(tacticalMap,convertCriteria));
		}
		
		return tacticalMapDTOs;

	}
	
	public TacticalMapDTO convertTacticalMapToTacticalMapDTO(TacticalMap tacticalMap, TacticalMapConvertCriteriaDTO convertCriteria) {
		
		TacticalMapDTO tacticalMapDTO = new TacticalMapDTO();
		
		tacticalMapDTO.setTacticalMapId(tacticalMap.getTacticalMapId());

	
		tacticalMapDTO.setRegion(tacticalMap.getRegion());

	
		tacticalMapDTO.setDateUpdated(tacticalMap.getDateUpdated());

	

		
		return tacticalMapDTO;
	}

	public ResultDTO updateTacticalMap(TacticalMapDTO tacticalMapDTO, RequestDTO requestDTO) {
		
		TacticalMap tacticalMap = tacticalMapDao.getById(tacticalMapDTO.getTacticalMapId());

		tacticalMap.setTacticalMapId(ControllerUtils.setValue(tacticalMap.getTacticalMapId(), tacticalMapDTO.getTacticalMapId()));

		tacticalMap.setRegion(ControllerUtils.setValue(tacticalMap.getRegion(), tacticalMapDTO.getRegion()));

		tacticalMap.setDateUpdated(ControllerUtils.setValue(tacticalMap.getDateUpdated(), tacticalMapDTO.getDateUpdated()));



        tacticalMap = tacticalMapDao.save(tacticalMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TacticalMapDTO getTacticalMapDTOById(Integer tacticalMapId) {
	
		TacticalMap tacticalMap = tacticalMapDao.getById(tacticalMapId);
			
		
		TacticalMapConvertCriteriaDTO convertCriteria = new TacticalMapConvertCriteriaDTO();
		return(this.convertTacticalMapToTacticalMapDTO(tacticalMap,convertCriteria));
	}







}
