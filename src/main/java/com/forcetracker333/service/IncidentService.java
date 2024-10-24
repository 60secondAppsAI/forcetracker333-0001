package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Incident;
import com.forcetracker333.dto.IncidentDTO;
import com.forcetracker333.dto.IncidentSearchDTO;
import com.forcetracker333.dto.IncidentPageDTO;
import com.forcetracker333.dto.IncidentConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface IncidentService extends GenericService<Incident, Integer> {

	List<Incident> findAll();

	ResultDTO addIncident(IncidentDTO incidentDTO, RequestDTO requestDTO);

	ResultDTO updateIncident(IncidentDTO incidentDTO, RequestDTO requestDTO);

    Page<Incident> getAllIncidents(Pageable pageable);

    Page<Incident> getAllIncidents(Specification<Incident> spec, Pageable pageable);

	ResponseEntity<IncidentPageDTO> getIncidents(IncidentSearchDTO incidentSearchDTO);
	
	List<IncidentDTO> convertIncidentsToIncidentDTOs(List<Incident> incidents, IncidentConvertCriteriaDTO convertCriteria);

	IncidentDTO getIncidentDTOById(Integer incidentId);







}





