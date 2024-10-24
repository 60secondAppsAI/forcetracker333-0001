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
import com.forcetracker333.dao.EquipmentDAO;
import com.forcetracker333.domain.Equipment;
import com.forcetracker333.dto.EquipmentDTO;
import com.forcetracker333.dto.EquipmentSearchDTO;
import com.forcetracker333.dto.EquipmentPageDTO;
import com.forcetracker333.dto.EquipmentConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.EquipmentService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class EquipmentServiceImpl extends GenericServiceImpl<Equipment, Integer> implements EquipmentService {

    private final static Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);

	@Autowired
	EquipmentDAO equipmentDao;

	


	@Override
	public GenericDAO<Equipment, Integer> getDAO() {
		return (GenericDAO<Equipment, Integer>) equipmentDao;
	}
	
	public List<Equipment> findAll () {
		List<Equipment> equipments = equipmentDao.findAll();
		
		return equipments;	
		
	}

	public ResultDTO addEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO) {

		Equipment equipment = new Equipment();

		equipment.setEquipmentId(equipmentDTO.getEquipmentId());


		equipment.setName(equipmentDTO.getName());


		equipment.setType(equipmentDTO.getType());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		equipment = equipmentDao.save(equipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Equipment> getAllEquipments(Pageable pageable) {
		return equipmentDao.findAll(pageable);
	}

	public Page<Equipment> getAllEquipments(Specification<Equipment> spec, Pageable pageable) {
		return equipmentDao.findAll(spec, pageable);
	}

	public ResponseEntity<EquipmentPageDTO> getEquipments(EquipmentSearchDTO equipmentSearchDTO) {
	
			Integer equipmentId = equipmentSearchDTO.getEquipmentId(); 
 			String name = equipmentSearchDTO.getName(); 
 			String type = equipmentSearchDTO.getType(); 
 			String sortBy = equipmentSearchDTO.getSortBy();
			String sortOrder = equipmentSearchDTO.getSortOrder();
			String searchQuery = equipmentSearchDTO.getSearchQuery();
			Integer page = equipmentSearchDTO.getPage();
			Integer size = equipmentSearchDTO.getSize();

	        Specification<Equipment> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, equipmentId, "equipmentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, type, "type"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("type")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Equipment> equipments = this.getAllEquipments(spec, pageable);
		
		//System.out.println(String.valueOf(equipments.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(equipments.getTotalPages()));
		
		List<Equipment> equipmentsList = equipments.getContent();
		
		EquipmentConvertCriteriaDTO convertCriteria = new EquipmentConvertCriteriaDTO();
		List<EquipmentDTO> equipmentDTOs = this.convertEquipmentsToEquipmentDTOs(equipmentsList,convertCriteria);
		
		EquipmentPageDTO equipmentPageDTO = new EquipmentPageDTO();
		equipmentPageDTO.setEquipments(equipmentDTOs);
		equipmentPageDTO.setTotalElements(equipments.getTotalElements());
		return ResponseEntity.ok(equipmentPageDTO);
	}

	public List<EquipmentDTO> convertEquipmentsToEquipmentDTOs(List<Equipment> equipments, EquipmentConvertCriteriaDTO convertCriteria) {
		
		List<EquipmentDTO> equipmentDTOs = new ArrayList<EquipmentDTO>();
		
		for (Equipment equipment : equipments) {
			equipmentDTOs.add(convertEquipmentToEquipmentDTO(equipment,convertCriteria));
		}
		
		return equipmentDTOs;

	}
	
	public EquipmentDTO convertEquipmentToEquipmentDTO(Equipment equipment, EquipmentConvertCriteriaDTO convertCriteria) {
		
		EquipmentDTO equipmentDTO = new EquipmentDTO();
		
		equipmentDTO.setEquipmentId(equipment.getEquipmentId());

	
		equipmentDTO.setName(equipment.getName());

	
		equipmentDTO.setType(equipment.getType());

	

		
		return equipmentDTO;
	}

	public ResultDTO updateEquipment(EquipmentDTO equipmentDTO, RequestDTO requestDTO) {
		
		Equipment equipment = equipmentDao.getById(equipmentDTO.getEquipmentId());

		equipment.setEquipmentId(ControllerUtils.setValue(equipment.getEquipmentId(), equipmentDTO.getEquipmentId()));

		equipment.setName(ControllerUtils.setValue(equipment.getName(), equipmentDTO.getName()));

		equipment.setType(ControllerUtils.setValue(equipment.getType(), equipmentDTO.getType()));



        equipment = equipmentDao.save(equipment);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EquipmentDTO getEquipmentDTOById(Integer equipmentId) {
	
		Equipment equipment = equipmentDao.getById(equipmentId);
			
		
		EquipmentConvertCriteriaDTO convertCriteria = new EquipmentConvertCriteriaDTO();
		return(this.convertEquipmentToEquipmentDTO(equipment,convertCriteria));
	}







}
