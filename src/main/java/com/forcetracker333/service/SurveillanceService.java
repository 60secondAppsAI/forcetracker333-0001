package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Surveillance;
import com.forcetracker333.dto.SurveillanceDTO;
import com.forcetracker333.dto.SurveillanceSearchDTO;
import com.forcetracker333.dto.SurveillancePageDTO;
import com.forcetracker333.dto.SurveillanceConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SurveillanceService extends GenericService<Surveillance, Integer> {

	List<Surveillance> findAll();

	ResultDTO addSurveillance(SurveillanceDTO surveillanceDTO, RequestDTO requestDTO);

	ResultDTO updateSurveillance(SurveillanceDTO surveillanceDTO, RequestDTO requestDTO);

    Page<Surveillance> getAllSurveillances(Pageable pageable);

    Page<Surveillance> getAllSurveillances(Specification<Surveillance> spec, Pageable pageable);

	ResponseEntity<SurveillancePageDTO> getSurveillances(SurveillanceSearchDTO surveillanceSearchDTO);
	
	List<SurveillanceDTO> convertSurveillancesToSurveillanceDTOs(List<Surveillance> surveillances, SurveillanceConvertCriteriaDTO convertCriteria);

	SurveillanceDTO getSurveillanceDTOById(Integer surveillanceId);







}





