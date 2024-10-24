package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.IntelligenceReport;
import com.forcetracker333.dto.IntelligenceReportDTO;
import com.forcetracker333.dto.IntelligenceReportSearchDTO;
import com.forcetracker333.dto.IntelligenceReportPageDTO;
import com.forcetracker333.dto.IntelligenceReportConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IntelligenceReportService extends GenericService<IntelligenceReport, Integer> {

	List<IntelligenceReport> findAll();

	ResultDTO addIntelligenceReport(IntelligenceReportDTO intelligenceReportDTO, RequestDTO requestDTO);

	ResultDTO updateIntelligenceReport(IntelligenceReportDTO intelligenceReportDTO, RequestDTO requestDTO);

    Page<IntelligenceReport> getAllIntelligenceReports(Pageable pageable);

    Page<IntelligenceReport> getAllIntelligenceReports(Specification<IntelligenceReport> spec, Pageable pageable);

	ResponseEntity<IntelligenceReportPageDTO> getIntelligenceReports(IntelligenceReportSearchDTO intelligenceReportSearchDTO);
	
	List<IntelligenceReportDTO> convertIntelligenceReportsToIntelligenceReportDTOs(List<IntelligenceReport> intelligenceReports, IntelligenceReportConvertCriteriaDTO convertCriteria);

	IntelligenceReportDTO getIntelligenceReportDTOById(Integer intelligenceReportId);







}





