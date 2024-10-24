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
import com.forcetracker333.dao.SurveillanceDAO;
import com.forcetracker333.domain.Surveillance;
import com.forcetracker333.dto.SurveillanceDTO;
import com.forcetracker333.dto.SurveillanceSearchDTO;
import com.forcetracker333.dto.SurveillancePageDTO;
import com.forcetracker333.dto.SurveillanceConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.SurveillanceService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class SurveillanceServiceImpl extends GenericServiceImpl<Surveillance, Integer> implements SurveillanceService {

    private final static Logger logger = LoggerFactory.getLogger(SurveillanceServiceImpl.class);

	@Autowired
	SurveillanceDAO surveillanceDao;

	


	@Override
	public GenericDAO<Surveillance, Integer> getDAO() {
		return (GenericDAO<Surveillance, Integer>) surveillanceDao;
	}
	
	public List<Surveillance> findAll () {
		List<Surveillance> surveillances = surveillanceDao.findAll();
		
		return surveillances;	
		
	}

	public ResultDTO addSurveillance(SurveillanceDTO surveillanceDTO, RequestDTO requestDTO) {

		Surveillance surveillance = new Surveillance();

		surveillance.setSurveillanceId(surveillanceDTO.getSurveillanceId());


		surveillance.setAreaMonitored(surveillanceDTO.getAreaMonitored());


		surveillance.setStartDate(surveillanceDTO.getStartDate());


		surveillance.setEndDate(surveillanceDTO.getEndDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		surveillance = surveillanceDao.save(surveillance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Surveillance> getAllSurveillances(Pageable pageable) {
		return surveillanceDao.findAll(pageable);
	}

	public Page<Surveillance> getAllSurveillances(Specification<Surveillance> spec, Pageable pageable) {
		return surveillanceDao.findAll(spec, pageable);
	}

	public ResponseEntity<SurveillancePageDTO> getSurveillances(SurveillanceSearchDTO surveillanceSearchDTO) {
	
			Integer surveillanceId = surveillanceSearchDTO.getSurveillanceId(); 
 			String areaMonitored = surveillanceSearchDTO.getAreaMonitored(); 
     			String sortBy = surveillanceSearchDTO.getSortBy();
			String sortOrder = surveillanceSearchDTO.getSortOrder();
			String searchQuery = surveillanceSearchDTO.getSearchQuery();
			Integer page = surveillanceSearchDTO.getPage();
			Integer size = surveillanceSearchDTO.getSize();

	        Specification<Surveillance> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, surveillanceId, "surveillanceId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, areaMonitored, "areaMonitored"); 
			
 			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("areaMonitored")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Surveillance> surveillances = this.getAllSurveillances(spec, pageable);
		
		//System.out.println(String.valueOf(surveillances.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(surveillances.getTotalPages()));
		
		List<Surveillance> surveillancesList = surveillances.getContent();
		
		SurveillanceConvertCriteriaDTO convertCriteria = new SurveillanceConvertCriteriaDTO();
		List<SurveillanceDTO> surveillanceDTOs = this.convertSurveillancesToSurveillanceDTOs(surveillancesList,convertCriteria);
		
		SurveillancePageDTO surveillancePageDTO = new SurveillancePageDTO();
		surveillancePageDTO.setSurveillances(surveillanceDTOs);
		surveillancePageDTO.setTotalElements(surveillances.getTotalElements());
		return ResponseEntity.ok(surveillancePageDTO);
	}

	public List<SurveillanceDTO> convertSurveillancesToSurveillanceDTOs(List<Surveillance> surveillances, SurveillanceConvertCriteriaDTO convertCriteria) {
		
		List<SurveillanceDTO> surveillanceDTOs = new ArrayList<SurveillanceDTO>();
		
		for (Surveillance surveillance : surveillances) {
			surveillanceDTOs.add(convertSurveillanceToSurveillanceDTO(surveillance,convertCriteria));
		}
		
		return surveillanceDTOs;

	}
	
	public SurveillanceDTO convertSurveillanceToSurveillanceDTO(Surveillance surveillance, SurveillanceConvertCriteriaDTO convertCriteria) {
		
		SurveillanceDTO surveillanceDTO = new SurveillanceDTO();
		
		surveillanceDTO.setSurveillanceId(surveillance.getSurveillanceId());

	
		surveillanceDTO.setAreaMonitored(surveillance.getAreaMonitored());

	
		surveillanceDTO.setStartDate(surveillance.getStartDate());

	
		surveillanceDTO.setEndDate(surveillance.getEndDate());

	

		
		return surveillanceDTO;
	}

	public ResultDTO updateSurveillance(SurveillanceDTO surveillanceDTO, RequestDTO requestDTO) {
		
		Surveillance surveillance = surveillanceDao.getById(surveillanceDTO.getSurveillanceId());

		surveillance.setSurveillanceId(ControllerUtils.setValue(surveillance.getSurveillanceId(), surveillanceDTO.getSurveillanceId()));

		surveillance.setAreaMonitored(ControllerUtils.setValue(surveillance.getAreaMonitored(), surveillanceDTO.getAreaMonitored()));

		surveillance.setStartDate(ControllerUtils.setValue(surveillance.getStartDate(), surveillanceDTO.getStartDate()));

		surveillance.setEndDate(ControllerUtils.setValue(surveillance.getEndDate(), surveillanceDTO.getEndDate()));



        surveillance = surveillanceDao.save(surveillance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SurveillanceDTO getSurveillanceDTOById(Integer surveillanceId) {
	
		Surveillance surveillance = surveillanceDao.getById(surveillanceId);
			
		
		SurveillanceConvertCriteriaDTO convertCriteria = new SurveillanceConvertCriteriaDTO();
		return(this.convertSurveillanceToSurveillanceDTO(surveillance,convertCriteria));
	}







}
