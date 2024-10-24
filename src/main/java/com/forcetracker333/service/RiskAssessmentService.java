package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.RiskAssessment;
import com.forcetracker333.dto.RiskAssessmentDTO;
import com.forcetracker333.dto.RiskAssessmentSearchDTO;
import com.forcetracker333.dto.RiskAssessmentPageDTO;
import com.forcetracker333.dto.RiskAssessmentConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RiskAssessmentService extends GenericService<RiskAssessment, Integer> {

	List<RiskAssessment> findAll();

	ResultDTO addRiskAssessment(RiskAssessmentDTO riskAssessmentDTO, RequestDTO requestDTO);

	ResultDTO updateRiskAssessment(RiskAssessmentDTO riskAssessmentDTO, RequestDTO requestDTO);

    Page<RiskAssessment> getAllRiskAssessments(Pageable pageable);

    Page<RiskAssessment> getAllRiskAssessments(Specification<RiskAssessment> spec, Pageable pageable);

	ResponseEntity<RiskAssessmentPageDTO> getRiskAssessments(RiskAssessmentSearchDTO riskAssessmentSearchDTO);
	
	List<RiskAssessmentDTO> convertRiskAssessmentsToRiskAssessmentDTOs(List<RiskAssessment> riskAssessments, RiskAssessmentConvertCriteriaDTO convertCriteria);

	RiskAssessmentDTO getRiskAssessmentDTOById(Integer riskAssessmentId);







}





