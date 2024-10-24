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
import com.forcetracker333.dao.FleetDAO;
import com.forcetracker333.domain.Fleet;
import com.forcetracker333.dto.FleetDTO;
import com.forcetracker333.dto.FleetSearchDTO;
import com.forcetracker333.dto.FleetPageDTO;
import com.forcetracker333.dto.FleetConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.FleetService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class FleetServiceImpl extends GenericServiceImpl<Fleet, Integer> implements FleetService {

    private final static Logger logger = LoggerFactory.getLogger(FleetServiceImpl.class);

	@Autowired
	FleetDAO fleetDao;

	


	@Override
	public GenericDAO<Fleet, Integer> getDAO() {
		return (GenericDAO<Fleet, Integer>) fleetDao;
	}
	
	public List<Fleet> findAll () {
		List<Fleet> fleets = fleetDao.findAll();
		
		return fleets;	
		
	}

	public ResultDTO addFleet(FleetDTO fleetDTO, RequestDTO requestDTO) {

		Fleet fleet = new Fleet();

		fleet.setFleetId(fleetDTO.getFleetId());


		fleet.setName(fleetDTO.getName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		fleet = fleetDao.save(fleet);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Fleet> getAllFleets(Pageable pageable) {
		return fleetDao.findAll(pageable);
	}

	public Page<Fleet> getAllFleets(Specification<Fleet> spec, Pageable pageable) {
		return fleetDao.findAll(spec, pageable);
	}

	public ResponseEntity<FleetPageDTO> getFleets(FleetSearchDTO fleetSearchDTO) {
	
			Integer fleetId = fleetSearchDTO.getFleetId(); 
 			String name = fleetSearchDTO.getName(); 
 			String sortBy = fleetSearchDTO.getSortBy();
			String sortOrder = fleetSearchDTO.getSortOrder();
			String searchQuery = fleetSearchDTO.getSearchQuery();
			Integer page = fleetSearchDTO.getPage();
			Integer size = fleetSearchDTO.getSize();

	        Specification<Fleet> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, fleetId, "fleetId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			

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

		Page<Fleet> fleets = this.getAllFleets(spec, pageable);
		
		//System.out.println(String.valueOf(fleets.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(fleets.getTotalPages()));
		
		List<Fleet> fleetsList = fleets.getContent();
		
		FleetConvertCriteriaDTO convertCriteria = new FleetConvertCriteriaDTO();
		List<FleetDTO> fleetDTOs = this.convertFleetsToFleetDTOs(fleetsList,convertCriteria);
		
		FleetPageDTO fleetPageDTO = new FleetPageDTO();
		fleetPageDTO.setFleets(fleetDTOs);
		fleetPageDTO.setTotalElements(fleets.getTotalElements());
		return ResponseEntity.ok(fleetPageDTO);
	}

	public List<FleetDTO> convertFleetsToFleetDTOs(List<Fleet> fleets, FleetConvertCriteriaDTO convertCriteria) {
		
		List<FleetDTO> fleetDTOs = new ArrayList<FleetDTO>();
		
		for (Fleet fleet : fleets) {
			fleetDTOs.add(convertFleetToFleetDTO(fleet,convertCriteria));
		}
		
		return fleetDTOs;

	}
	
	public FleetDTO convertFleetToFleetDTO(Fleet fleet, FleetConvertCriteriaDTO convertCriteria) {
		
		FleetDTO fleetDTO = new FleetDTO();
		
		fleetDTO.setFleetId(fleet.getFleetId());

	
		fleetDTO.setName(fleet.getName());

	

		
		return fleetDTO;
	}

	public ResultDTO updateFleet(FleetDTO fleetDTO, RequestDTO requestDTO) {
		
		Fleet fleet = fleetDao.getById(fleetDTO.getFleetId());

		fleet.setFleetId(ControllerUtils.setValue(fleet.getFleetId(), fleetDTO.getFleetId()));

		fleet.setName(ControllerUtils.setValue(fleet.getName(), fleetDTO.getName()));



        fleet = fleetDao.save(fleet);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FleetDTO getFleetDTOById(Integer fleetId) {
	
		Fleet fleet = fleetDao.getById(fleetId);
			
		
		FleetConvertCriteriaDTO convertCriteria = new FleetConvertCriteriaDTO();
		return(this.convertFleetToFleetDTO(fleet,convertCriteria));
	}







}
