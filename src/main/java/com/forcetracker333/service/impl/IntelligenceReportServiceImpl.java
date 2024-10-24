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
import com.forcetracker333.dao.IntelligenceReportDAO;
import com.forcetracker333.domain.IntelligenceReport;
import com.forcetracker333.dto.IntelligenceReportDTO;
import com.forcetracker333.dto.IntelligenceReportSearchDTO;
import com.forcetracker333.dto.IntelligenceReportPageDTO;
import com.forcetracker333.dto.IntelligenceReportConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.IntelligenceReportService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class IntelligenceReportServiceImpl extends GenericServiceImpl<IntelligenceReport, Integer> implements IntelligenceReportService {

    private final static Logger logger = LoggerFactory.getLogger(IntelligenceReportServiceImpl.class);

	@Autowired
	IntelligenceReportDAO intelligenceReportDao;

	


	@Override
	public GenericDAO<IntelligenceReport, Integer> getDAO() {
		return (GenericDAO<IntelligenceReport, Integer>) intelligenceReportDao;
	}
	
	public List<IntelligenceReport> findAll () {
		List<IntelligenceReport> intelligenceReports = intelligenceReportDao.findAll();
		
		return intelligenceReports;	
		
	}

	public ResultDTO addIntelligenceReport(IntelligenceReportDTO intelligenceReportDTO, RequestDTO requestDTO) {

		IntelligenceReport intelligenceReport = new IntelligenceReport();

		intelligenceReport.setIntelligenceReportId(intelligenceReportDTO.getIntelligenceReportId());


		intelligenceReport.setSummary(intelligenceReportDTO.getSummary());


		intelligenceReport.setDate(intelligenceReportDTO.getDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		intelligenceReport = intelligenceReportDao.save(intelligenceReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<IntelligenceReport> getAllIntelligenceReports(Pageable pageable) {
		return intelligenceReportDao.findAll(pageable);
	}

	public Page<IntelligenceReport> getAllIntelligenceReports(Specification<IntelligenceReport> spec, Pageable pageable) {
		return intelligenceReportDao.findAll(spec, pageable);
	}

	public ResponseEntity<IntelligenceReportPageDTO> getIntelligenceReports(IntelligenceReportSearchDTO intelligenceReportSearchDTO) {
	
			Integer intelligenceReportId = intelligenceReportSearchDTO.getIntelligenceReportId(); 
 			String summary = intelligenceReportSearchDTO.getSummary(); 
   			String sortBy = intelligenceReportSearchDTO.getSortBy();
			String sortOrder = intelligenceReportSearchDTO.getSortOrder();
			String searchQuery = intelligenceReportSearchDTO.getSearchQuery();
			Integer page = intelligenceReportSearchDTO.getPage();
			Integer size = intelligenceReportSearchDTO.getSize();

	        Specification<IntelligenceReport> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, intelligenceReportId, "intelligenceReportId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, summary, "summary"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("summary")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<IntelligenceReport> intelligenceReports = this.getAllIntelligenceReports(spec, pageable);
		
		//System.out.println(String.valueOf(intelligenceReports.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(intelligenceReports.getTotalPages()));
		
		List<IntelligenceReport> intelligenceReportsList = intelligenceReports.getContent();
		
		IntelligenceReportConvertCriteriaDTO convertCriteria = new IntelligenceReportConvertCriteriaDTO();
		List<IntelligenceReportDTO> intelligenceReportDTOs = this.convertIntelligenceReportsToIntelligenceReportDTOs(intelligenceReportsList,convertCriteria);
		
		IntelligenceReportPageDTO intelligenceReportPageDTO = new IntelligenceReportPageDTO();
		intelligenceReportPageDTO.setIntelligenceReports(intelligenceReportDTOs);
		intelligenceReportPageDTO.setTotalElements(intelligenceReports.getTotalElements());
		return ResponseEntity.ok(intelligenceReportPageDTO);
	}

	public List<IntelligenceReportDTO> convertIntelligenceReportsToIntelligenceReportDTOs(List<IntelligenceReport> intelligenceReports, IntelligenceReportConvertCriteriaDTO convertCriteria) {
		
		List<IntelligenceReportDTO> intelligenceReportDTOs = new ArrayList<IntelligenceReportDTO>();
		
		for (IntelligenceReport intelligenceReport : intelligenceReports) {
			intelligenceReportDTOs.add(convertIntelligenceReportToIntelligenceReportDTO(intelligenceReport,convertCriteria));
		}
		
		return intelligenceReportDTOs;

	}
	
	public IntelligenceReportDTO convertIntelligenceReportToIntelligenceReportDTO(IntelligenceReport intelligenceReport, IntelligenceReportConvertCriteriaDTO convertCriteria) {
		
		IntelligenceReportDTO intelligenceReportDTO = new IntelligenceReportDTO();
		
		intelligenceReportDTO.setIntelligenceReportId(intelligenceReport.getIntelligenceReportId());

	
		intelligenceReportDTO.setSummary(intelligenceReport.getSummary());

	
		intelligenceReportDTO.setDate(intelligenceReport.getDate());

	

		
		return intelligenceReportDTO;
	}

	public ResultDTO updateIntelligenceReport(IntelligenceReportDTO intelligenceReportDTO, RequestDTO requestDTO) {
		
		IntelligenceReport intelligenceReport = intelligenceReportDao.getById(intelligenceReportDTO.getIntelligenceReportId());

		intelligenceReport.setIntelligenceReportId(ControllerUtils.setValue(intelligenceReport.getIntelligenceReportId(), intelligenceReportDTO.getIntelligenceReportId()));

		intelligenceReport.setSummary(ControllerUtils.setValue(intelligenceReport.getSummary(), intelligenceReportDTO.getSummary()));

		intelligenceReport.setDate(ControllerUtils.setValue(intelligenceReport.getDate(), intelligenceReportDTO.getDate()));



        intelligenceReport = intelligenceReportDao.save(intelligenceReport);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public IntelligenceReportDTO getIntelligenceReportDTOById(Integer intelligenceReportId) {
	
		IntelligenceReport intelligenceReport = intelligenceReportDao.getById(intelligenceReportId);
			
		
		IntelligenceReportConvertCriteriaDTO convertCriteria = new IntelligenceReportConvertCriteriaDTO();
		return(this.convertIntelligenceReportToIntelligenceReportDTO(intelligenceReport,convertCriteria));
	}







}
