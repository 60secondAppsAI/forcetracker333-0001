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
import com.forcetracker333.dao.MilitaryUnitDAO;
import com.forcetracker333.domain.MilitaryUnit;
import com.forcetracker333.dto.MilitaryUnitDTO;
import com.forcetracker333.dto.MilitaryUnitSearchDTO;
import com.forcetracker333.dto.MilitaryUnitPageDTO;
import com.forcetracker333.dto.MilitaryUnitConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.MilitaryUnitService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class MilitaryUnitServiceImpl extends GenericServiceImpl<MilitaryUnit, Integer> implements MilitaryUnitService {

    private final static Logger logger = LoggerFactory.getLogger(MilitaryUnitServiceImpl.class);

	@Autowired
	MilitaryUnitDAO militaryUnitDao;

	


	@Override
	public GenericDAO<MilitaryUnit, Integer> getDAO() {
		return (GenericDAO<MilitaryUnit, Integer>) militaryUnitDao;
	}
	
	public List<MilitaryUnit> findAll () {
		List<MilitaryUnit> militaryUnits = militaryUnitDao.findAll();
		
		return militaryUnits;	
		
	}

	public ResultDTO addMilitaryUnit(MilitaryUnitDTO militaryUnitDTO, RequestDTO requestDTO) {

		MilitaryUnit militaryUnit = new MilitaryUnit();

		militaryUnit.setMilitaryUnitId(militaryUnitDTO.getMilitaryUnitId());


		militaryUnit.setName(militaryUnitDTO.getName());


		militaryUnit.setType(militaryUnitDTO.getType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		militaryUnit = militaryUnitDao.save(militaryUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MilitaryUnit> getAllMilitaryUnits(Pageable pageable) {
		return militaryUnitDao.findAll(pageable);
	}

	public Page<MilitaryUnit> getAllMilitaryUnits(Specification<MilitaryUnit> spec, Pageable pageable) {
		return militaryUnitDao.findAll(spec, pageable);
	}

	public ResponseEntity<MilitaryUnitPageDTO> getMilitaryUnits(MilitaryUnitSearchDTO militaryUnitSearchDTO) {
	
			Integer militaryUnitId = militaryUnitSearchDTO.getMilitaryUnitId(); 
 			String name = militaryUnitSearchDTO.getName(); 
 			String type = militaryUnitSearchDTO.getType(); 
 			String sortBy = militaryUnitSearchDTO.getSortBy();
			String sortOrder = militaryUnitSearchDTO.getSortOrder();
			String searchQuery = militaryUnitSearchDTO.getSearchQuery();
			Integer page = militaryUnitSearchDTO.getPage();
			Integer size = militaryUnitSearchDTO.getSize();

	        Specification<MilitaryUnit> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, militaryUnitId, "militaryUnitId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MilitaryUnit> militaryUnits = this.getAllMilitaryUnits(spec, pageable);
		
		//System.out.println(String.valueOf(militaryUnits.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(militaryUnits.getTotalPages()));
		
		List<MilitaryUnit> militaryUnitsList = militaryUnits.getContent();
		
		MilitaryUnitConvertCriteriaDTO convertCriteria = new MilitaryUnitConvertCriteriaDTO();
		List<MilitaryUnitDTO> militaryUnitDTOs = this.convertMilitaryUnitsToMilitaryUnitDTOs(militaryUnitsList,convertCriteria);
		
		MilitaryUnitPageDTO militaryUnitPageDTO = new MilitaryUnitPageDTO();
		militaryUnitPageDTO.setMilitaryUnits(militaryUnitDTOs);
		militaryUnitPageDTO.setTotalElements(militaryUnits.getTotalElements());
		return ResponseEntity.ok(militaryUnitPageDTO);
	}

	public List<MilitaryUnitDTO> convertMilitaryUnitsToMilitaryUnitDTOs(List<MilitaryUnit> militaryUnits, MilitaryUnitConvertCriteriaDTO convertCriteria) {
		
		List<MilitaryUnitDTO> militaryUnitDTOs = new ArrayList<MilitaryUnitDTO>();
		
		for (MilitaryUnit militaryUnit : militaryUnits) {
			militaryUnitDTOs.add(convertMilitaryUnitToMilitaryUnitDTO(militaryUnit,convertCriteria));
		}
		
		return militaryUnitDTOs;

	}
	
	public MilitaryUnitDTO convertMilitaryUnitToMilitaryUnitDTO(MilitaryUnit militaryUnit, MilitaryUnitConvertCriteriaDTO convertCriteria) {
		
		MilitaryUnitDTO militaryUnitDTO = new MilitaryUnitDTO();
		
		militaryUnitDTO.setMilitaryUnitId(militaryUnit.getMilitaryUnitId());

	
		militaryUnitDTO.setName(militaryUnit.getName());

	
		militaryUnitDTO.setType(militaryUnit.getType());

	

		
		return militaryUnitDTO;
	}

	public ResultDTO updateMilitaryUnit(MilitaryUnitDTO militaryUnitDTO, RequestDTO requestDTO) {
		
		MilitaryUnit militaryUnit = militaryUnitDao.getById(militaryUnitDTO.getMilitaryUnitId());

		militaryUnit.setMilitaryUnitId(ControllerUtils.setValue(militaryUnit.getMilitaryUnitId(), militaryUnitDTO.getMilitaryUnitId()));

		militaryUnit.setName(ControllerUtils.setValue(militaryUnit.getName(), militaryUnitDTO.getName()));

		militaryUnit.setType(ControllerUtils.setValue(militaryUnit.getType(), militaryUnitDTO.getType()));



        militaryUnit = militaryUnitDao.save(militaryUnit);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MilitaryUnitDTO getMilitaryUnitDTOById(Integer militaryUnitId) {
	
		MilitaryUnit militaryUnit = militaryUnitDao.getById(militaryUnitId);
			
		
		MilitaryUnitConvertCriteriaDTO convertCriteria = new MilitaryUnitConvertCriteriaDTO();
		return(this.convertMilitaryUnitToMilitaryUnitDTO(militaryUnit,convertCriteria));
	}







}
