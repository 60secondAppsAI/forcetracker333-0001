package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Equipment;
import com.forcetracker333.dto.EquipmentDTO;
import com.forcetracker333.dto.EquipmentSearchDTO;
import com.forcetracker333.dto.EquipmentPageDTO;
import com.forcetracker333.dto.EquipmentConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EquipmentService extends GenericService<Equipment, Integer> {

	List<Equipment> findAll();

	ResultDTO addEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

	ResultDTO updateEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO);

    Page<Equipment> getAllEquipments(Pageable pageable);

    Page<Equipment> getAllEquipments(Specification<Equipment> spec, Pageable pageable);

	ResponseEntity<EquipmentPageDTO> getEquipments(EquipmentSearchDTO equipmentSearchDTO);
	
	List<EquipmentDTO> convertEquipmentsToEquipmentDTOs(List<Equipment> equipments, EquipmentConvertCriteriaDTO convertCriteria);

	EquipmentDTO getEquipmentDTOById(Integer equipmentId);







}





