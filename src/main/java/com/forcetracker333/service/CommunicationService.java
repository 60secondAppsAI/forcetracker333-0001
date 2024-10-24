package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Communication;
import com.forcetracker333.dto.CommunicationDTO;
import com.forcetracker333.dto.CommunicationSearchDTO;
import com.forcetracker333.dto.CommunicationPageDTO;
import com.forcetracker333.dto.CommunicationConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CommunicationService extends GenericService<Communication, Integer> {

	List<Communication> findAll();

	ResultDTO addCommunication(CommunicationDTO communicationDTO, RequestDTO requestDTO);

	ResultDTO updateCommunication(CommunicationDTO communicationDTO, RequestDTO requestDTO);

    Page<Communication> getAllCommunications(Pageable pageable);

    Page<Communication> getAllCommunications(Specification<Communication> spec, Pageable pageable);

	ResponseEntity<CommunicationPageDTO> getCommunications(CommunicationSearchDTO communicationSearchDTO);
	
	List<CommunicationDTO> convertCommunicationsToCommunicationDTOs(List<Communication> communications, CommunicationConvertCriteriaDTO convertCriteria);

	CommunicationDTO getCommunicationDTOById(Integer communicationId);







}





