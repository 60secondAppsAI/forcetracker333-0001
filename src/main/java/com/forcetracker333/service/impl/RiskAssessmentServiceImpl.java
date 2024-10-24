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
import com.forcetracker333.dao.RiskAssessmentDAO;
import com.forcetracker333.domain.RiskAssessment;
import com.forcetracker333.dto.RiskAssessmentDTO;
import com.forcetracker333.dto.RiskAssessmentSearchDTO;
import com.forcetracker333.dto.RiskAssessmentPageDTO;
import com.forcetracker333.dto.RiskAssessmentConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.RiskAssessmentService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class RiskAssessmentServiceImpl extends GenericServiceImpl<RiskAssessment, Integer> implements RiskAssessmentService {

    private final static Logger logger = LoggerFactory.getLogger(RiskAssessmentServiceImpl.class);

	@Autowired
	RiskAssessmentDAO riskAssessmentDao;

	


	@Override
	public GenericDAO<RiskAssessment, Integer> getDAO() {
		return (GenericDAO<RiskAssessment, Integer>) riskAssessmentDao;
	}
	
	public List<RiskAssessment> findAll () {
		List<RiskAssessment> riskAssessments = riskAssessmentDao.findAll();
		
		return riskAssessments;	
		
	}

	public ResultDTO addRiskAssessment(RiskAssessmentDTO riskAssessmentDTO, RequestDTO requestDTO) {

		RiskAssessment riskAssessment = new RiskAssessment();

		riskAssessment.setRiskAssessmentId(riskAssessmentDTO.getRiskAssessmentId());


		riskAssessment.setLevel(riskAssessmentDTO.getLevel());


		riskAssessment.setDate(riskAssessmentDTO.getDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		riskAssessment = riskAssessmentDao.save(riskAssessment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RiskAssessment> getAllRiskAssessments(Pageable pageable) {
		return riskAssessmentDao.findAll(pageable);
	}

	public Page<RiskAssessment> getAllRiskAssessments(Specification<RiskAssessment> spec, Pageable pageable) {
		return riskAssessmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<RiskAssessmentPageDTO> getRiskAssessments(RiskAssessmentSearchDTO riskAssessmentSearchDTO) {
	
			Integer riskAssessmentId = riskAssessmentSearchDTO.getRiskAssessmentId(); 
 			String level = riskAssessmentSearchDTO.getLevel(); 
   			String sortBy = riskAssessmentSearchDTO.getSortBy();
			String sortOrder = riskAssessmentSearchDTO.getSortOrder();
			String searchQuery = riskAssessmentSearchDTO.getSearchQuery();
			Integer page = riskAssessmentSearchDTO.getPage();
			Integer size = riskAssessmentSearchDTO.getSize();

	        Specification<RiskAssessment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, riskAssessmentId, "riskAssessmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, level, "level"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("level")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<RiskAssessment> riskAssessments = this.getAllRiskAssessments(spec, pageable);
		
		//System.out.println(String.valueOf(riskAssessments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(riskAssessments.getTotalPages()));
		
		List<RiskAssessment> riskAssessmentsList = riskAssessments.getContent();
		
		RiskAssessmentConvertCriteriaDTO convertCriteria = new RiskAssessmentConvertCriteriaDTO();
		List<RiskAssessmentDTO> riskAssessmentDTOs = this.convertRiskAssessmentsToRiskAssessmentDTOs(riskAssessmentsList,convertCriteria);
		
		RiskAssessmentPageDTO riskAssessmentPageDTO = new RiskAssessmentPageDTO();
		riskAssessmentPageDTO.setRiskAssessments(riskAssessmentDTOs);
		riskAssessmentPageDTO.setTotalElements(riskAssessments.getTotalElements());
		return ResponseEntity.ok(riskAssessmentPageDTO);
	}

	public List<RiskAssessmentDTO> convertRiskAssessmentsToRiskAssessmentDTOs(List<RiskAssessment> riskAssessments, RiskAssessmentConvertCriteriaDTO convertCriteria) {
		
		List<RiskAssessmentDTO> riskAssessmentDTOs = new ArrayList<RiskAssessmentDTO>();
		
		for (RiskAssessment riskAssessment : riskAssessments) {
			riskAssessmentDTOs.add(convertRiskAssessmentToRiskAssessmentDTO(riskAssessment,convertCriteria));
		}
		
		return riskAssessmentDTOs;

	}
	
	public RiskAssessmentDTO convertRiskAssessmentToRiskAssessmentDTO(RiskAssessment riskAssessment, RiskAssessmentConvertCriteriaDTO convertCriteria) {
		
		RiskAssessmentDTO riskAssessmentDTO = new RiskAssessmentDTO();
		
		riskAssessmentDTO.setRiskAssessmentId(riskAssessment.getRiskAssessmentId());

	
		riskAssessmentDTO.setLevel(riskAssessment.getLevel());

	
		riskAssessmentDTO.setDate(riskAssessment.getDate());

	

		
		return riskAssessmentDTO;
	}

	public ResultDTO updateRiskAssessment(RiskAssessmentDTO riskAssessmentDTO, RequestDTO requestDTO) {
		
		RiskAssessment riskAssessment = riskAssessmentDao.getById(riskAssessmentDTO.getRiskAssessmentId());

		riskAssessment.setRiskAssessmentId(ControllerUtils.setValue(riskAssessment.getRiskAssessmentId(), riskAssessmentDTO.getRiskAssessmentId()));

		riskAssessment.setLevel(ControllerUtils.setValue(riskAssessment.getLevel(), riskAssessmentDTO.getLevel()));

		riskAssessment.setDate(ControllerUtils.setValue(riskAssessment.getDate(), riskAssessmentDTO.getDate()));



        riskAssessment = riskAssessmentDao.save(riskAssessment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RiskAssessmentDTO getRiskAssessmentDTOById(Integer riskAssessmentId) {
	
		RiskAssessment riskAssessment = riskAssessmentDao.getById(riskAssessmentId);
			
		
		RiskAssessmentConvertCriteriaDTO convertCriteria = new RiskAssessmentConvertCriteriaDTO();
		return(this.convertRiskAssessmentToRiskAssessmentDTO(riskAssessment,convertCriteria));
	}







}
