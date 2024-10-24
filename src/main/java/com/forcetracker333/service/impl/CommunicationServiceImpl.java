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
import com.forcetracker333.dao.CommunicationDAO;
import com.forcetracker333.domain.Communication;
import com.forcetracker333.dto.CommunicationDTO;
import com.forcetracker333.dto.CommunicationSearchDTO;
import com.forcetracker333.dto.CommunicationPageDTO;
import com.forcetracker333.dto.CommunicationConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.CommunicationService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class CommunicationServiceImpl extends GenericServiceImpl<Communication, Integer> implements CommunicationService {

    private final static Logger logger = LoggerFactory.getLogger(CommunicationServiceImpl.class);

	@Autowired
	CommunicationDAO communicationDao;

	


	@Override
	public GenericDAO<Communication, Integer> getDAO() {
		return (GenericDAO<Communication, Integer>) communicationDao;
	}
	
	public List<Communication> findAll () {
		List<Communication> communications = communicationDao.findAll();
		
		return communications;	
		
	}

	public ResultDTO addCommunication(CommunicationDTO communicationDTO, RequestDTO requestDTO) {

		Communication communication = new Communication();

		communication.setCommunicationId(communicationDTO.getCommunicationId());


		communication.setType(communicationDTO.getType());


		communication.setContent(communicationDTO.getContent());


		communication.setDateTime(communicationDTO.getDateTime());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		communication = communicationDao.save(communication);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Communication> getAllCommunications(Pageable pageable) {
		return communicationDao.findAll(pageable);
	}

	public Page<Communication> getAllCommunications(Specification<Communication> spec, Pageable pageable) {
		return communicationDao.findAll(spec, pageable);
	}

	public ResponseEntity<CommunicationPageDTO> getCommunications(CommunicationSearchDTO communicationSearchDTO) {
	
			Integer communicationId = communicationSearchDTO.getCommunicationId(); 
 			String type = communicationSearchDTO.getType(); 
 			String content = communicationSearchDTO.getContent(); 
  			String sortBy = communicationSearchDTO.getSortBy();
			String sortOrder = communicationSearchDTO.getSortOrder();
			String searchQuery = communicationSearchDTO.getSearchQuery();
			Integer page = communicationSearchDTO.getPage();
			Integer size = communicationSearchDTO.getSize();

	        Specification<Communication> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, communicationId, "communicationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			
			spec = ControllerUtils.andIfNecessary(spec, content, "content"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("content")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Communication> communications = this.getAllCommunications(spec, pageable);
		
		//System.out.println(String.valueOf(communications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(communications.getTotalPages()));
		
		List<Communication> communicationsList = communications.getContent();
		
		CommunicationConvertCriteriaDTO convertCriteria = new CommunicationConvertCriteriaDTO();
		List<CommunicationDTO> communicationDTOs = this.convertCommunicationsToCommunicationDTOs(communicationsList,convertCriteria);
		
		CommunicationPageDTO communicationPageDTO = new CommunicationPageDTO();
		communicationPageDTO.setCommunications(communicationDTOs);
		communicationPageDTO.setTotalElements(communications.getTotalElements());
		return ResponseEntity.ok(communicationPageDTO);
	}

	public List<CommunicationDTO> convertCommunicationsToCommunicationDTOs(List<Communication> communications, CommunicationConvertCriteriaDTO convertCriteria) {
		
		List<CommunicationDTO> communicationDTOs = new ArrayList<CommunicationDTO>();
		
		for (Communication communication : communications) {
			communicationDTOs.add(convertCommunicationToCommunicationDTO(communication,convertCriteria));
		}
		
		return communicationDTOs;

	}
	
	public CommunicationDTO convertCommunicationToCommunicationDTO(Communication communication, CommunicationConvertCriteriaDTO convertCriteria) {
		
		CommunicationDTO communicationDTO = new CommunicationDTO();
		
		communicationDTO.setCommunicationId(communication.getCommunicationId());

	
		communicationDTO.setType(communication.getType());

	
		communicationDTO.setContent(communication.getContent());

	
		communicationDTO.setDateTime(communication.getDateTime());

	

		
		return communicationDTO;
	}

	public ResultDTO updateCommunication(CommunicationDTO communicationDTO, RequestDTO requestDTO) {
		
		Communication communication = communicationDao.getById(communicationDTO.getCommunicationId());

		communication.setCommunicationId(ControllerUtils.setValue(communication.getCommunicationId(), communicationDTO.getCommunicationId()));

		communication.setType(ControllerUtils.setValue(communication.getType(), communicationDTO.getType()));

		communication.setContent(ControllerUtils.setValue(communication.getContent(), communicationDTO.getContent()));

		communication.setDateTime(ControllerUtils.setValue(communication.getDateTime(), communicationDTO.getDateTime()));



        communication = communicationDao.save(communication);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CommunicationDTO getCommunicationDTOById(Integer communicationId) {
	
		Communication communication = communicationDao.getById(communicationId);
			
		
		CommunicationConvertCriteriaDTO convertCriteria = new CommunicationConvertCriteriaDTO();
		return(this.convertCommunicationToCommunicationDTO(communication,convertCriteria));
	}







}
