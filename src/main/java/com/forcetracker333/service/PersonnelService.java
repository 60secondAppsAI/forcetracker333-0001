package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Personnel;
import com.forcetracker333.dto.PersonnelDTO;
import com.forcetracker333.dto.PersonnelSearchDTO;
import com.forcetracker333.dto.PersonnelPageDTO;
import com.forcetracker333.dto.PersonnelConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PersonnelService extends GenericService<Personnel, Integer> {

	List<Personnel> findAll();

	ResultDTO addPersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO);

	ResultDTO updatePersonnel(PersonnelDTO personnelDTO, RequestDTO requestDTO);

    Page<Personnel> getAllPersonnels(Pageable pageable);

    Page<Personnel> getAllPersonnels(Specification<Personnel> spec, Pageable pageable);

	ResponseEntity<PersonnelPageDTO> getPersonnels(PersonnelSearchDTO personnelSearchDTO);
	
	List<PersonnelDTO> convertPersonnelsToPersonnelDTOs(List<Personnel> personnels, PersonnelConvertCriteriaDTO convertCriteria);

	PersonnelDTO getPersonnelDTOById(Integer personnelId);







}





