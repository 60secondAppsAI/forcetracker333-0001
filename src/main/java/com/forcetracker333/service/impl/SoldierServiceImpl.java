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
import com.forcetracker333.dao.SoldierDAO;
import com.forcetracker333.domain.Soldier;
import com.forcetracker333.dto.SoldierDTO;
import com.forcetracker333.dto.SoldierSearchDTO;
import com.forcetracker333.dto.SoldierPageDTO;
import com.forcetracker333.dto.SoldierConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.SoldierService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class SoldierServiceImpl extends GenericServiceImpl<Soldier, Integer> implements SoldierService {

    private final static Logger logger = LoggerFactory.getLogger(SoldierServiceImpl.class);

	@Autowired
	SoldierDAO soldierDao;

	


	@Override
	public GenericDAO<Soldier, Integer> getDAO() {
		return (GenericDAO<Soldier, Integer>) soldierDao;
	}
	
	public List<Soldier> findAll () {
		List<Soldier> soldiers = soldierDao.findAll();
		
		return soldiers;	
		
	}

	public ResultDTO addSoldier(SoldierDTO soldierDTO, RequestDTO requestDTO) {

		Soldier soldier = new Soldier();

		soldier.setSoldierId(soldierDTO.getSoldierId());


		soldier.setName(soldierDTO.getName());


		soldier.setRank(soldierDTO.getRank());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		soldier = soldierDao.save(soldier);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Soldier> getAllSoldiers(Pageable pageable) {
		return soldierDao.findAll(pageable);
	}

	public Page<Soldier> getAllSoldiers(Specification<Soldier> spec, Pageable pageable) {
		return soldierDao.findAll(spec, pageable);
	}

	public ResponseEntity<SoldierPageDTO> getSoldiers(SoldierSearchDTO soldierSearchDTO) {
	
			Integer soldierId = soldierSearchDTO.getSoldierId(); 
 			String name = soldierSearchDTO.getName(); 
 			String rank = soldierSearchDTO.getRank(); 
 			String sortBy = soldierSearchDTO.getSortBy();
			String sortOrder = soldierSearchDTO.getSortOrder();
			String searchQuery = soldierSearchDTO.getSearchQuery();
			Integer page = soldierSearchDTO.getPage();
			Integer size = soldierSearchDTO.getSize();

	        Specification<Soldier> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, soldierId, "soldierId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, rank, "rank"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("rank")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Soldier> soldiers = this.getAllSoldiers(spec, pageable);
		
		//System.out.println(String.valueOf(soldiers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(soldiers.getTotalPages()));
		
		List<Soldier> soldiersList = soldiers.getContent();
		
		SoldierConvertCriteriaDTO convertCriteria = new SoldierConvertCriteriaDTO();
		List<SoldierDTO> soldierDTOs = this.convertSoldiersToSoldierDTOs(soldiersList,convertCriteria);
		
		SoldierPageDTO soldierPageDTO = new SoldierPageDTO();
		soldierPageDTO.setSoldiers(soldierDTOs);
		soldierPageDTO.setTotalElements(soldiers.getTotalElements());
		return ResponseEntity.ok(soldierPageDTO);
	}

	public List<SoldierDTO> convertSoldiersToSoldierDTOs(List<Soldier> soldiers, SoldierConvertCriteriaDTO convertCriteria) {
		
		List<SoldierDTO> soldierDTOs = new ArrayList<SoldierDTO>();
		
		for (Soldier soldier : soldiers) {
			soldierDTOs.add(convertSoldierToSoldierDTO(soldier,convertCriteria));
		}
		
		return soldierDTOs;

	}
	
	public SoldierDTO convertSoldierToSoldierDTO(Soldier soldier, SoldierConvertCriteriaDTO convertCriteria) {
		
		SoldierDTO soldierDTO = new SoldierDTO();
		
		soldierDTO.setSoldierId(soldier.getSoldierId());

	
		soldierDTO.setName(soldier.getName());

	
		soldierDTO.setRank(soldier.getRank());

	

		
		return soldierDTO;
	}

	public ResultDTO updateSoldier(SoldierDTO soldierDTO, RequestDTO requestDTO) {
		
		Soldier soldier = soldierDao.getById(soldierDTO.getSoldierId());

		soldier.setSoldierId(ControllerUtils.setValue(soldier.getSoldierId(), soldierDTO.getSoldierId()));

		soldier.setName(ControllerUtils.setValue(soldier.getName(), soldierDTO.getName()));

		soldier.setRank(ControllerUtils.setValue(soldier.getRank(), soldierDTO.getRank()));



        soldier = soldierDao.save(soldier);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SoldierDTO getSoldierDTOById(Integer soldierId) {
	
		Soldier soldier = soldierDao.getById(soldierId);
			
		
		SoldierConvertCriteriaDTO convertCriteria = new SoldierConvertCriteriaDTO();
		return(this.convertSoldierToSoldierDTO(soldier,convertCriteria));
	}







}
