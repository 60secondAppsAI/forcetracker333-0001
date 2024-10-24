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
import com.forcetracker333.dao.CommandCenterDAO;
import com.forcetracker333.domain.CommandCenter;
import com.forcetracker333.dto.CommandCenterDTO;
import com.forcetracker333.dto.CommandCenterSearchDTO;
import com.forcetracker333.dto.CommandCenterPageDTO;
import com.forcetracker333.dto.CommandCenterConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.CommandCenterService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class CommandCenterServiceImpl extends GenericServiceImpl<CommandCenter, Integer> implements CommandCenterService {

    private final static Logger logger = LoggerFactory.getLogger(CommandCenterServiceImpl.class);

	@Autowired
	CommandCenterDAO commandCenterDao;

	


	@Override
	public GenericDAO<CommandCenter, Integer> getDAO() {
		return (GenericDAO<CommandCenter, Integer>) commandCenterDao;
	}
	
	public List<CommandCenter> findAll () {
		List<CommandCenter> commandCenters = commandCenterDao.findAll();
		
		return commandCenters;	
		
	}

	public ResultDTO addCommandCenter(CommandCenterDTO commandCenterDTO, RequestDTO requestDTO) {

		CommandCenter commandCenter = new CommandCenter();

		commandCenter.setCommandCenterId(commandCenterDTO.getCommandCenterId());


		commandCenter.setName(commandCenterDTO.getName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		commandCenter = commandCenterDao.save(commandCenter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CommandCenter> getAllCommandCenters(Pageable pageable) {
		return commandCenterDao.findAll(pageable);
	}

	public Page<CommandCenter> getAllCommandCenters(Specification<CommandCenter> spec, Pageable pageable) {
		return commandCenterDao.findAll(spec, pageable);
	}

	public ResponseEntity<CommandCenterPageDTO> getCommandCenters(CommandCenterSearchDTO commandCenterSearchDTO) {
	
			Integer commandCenterId = commandCenterSearchDTO.getCommandCenterId(); 
 			String name = commandCenterSearchDTO.getName(); 
 			String sortBy = commandCenterSearchDTO.getSortBy();
			String sortOrder = commandCenterSearchDTO.getSortOrder();
			String searchQuery = commandCenterSearchDTO.getSearchQuery();
			Integer page = commandCenterSearchDTO.getPage();
			Integer size = commandCenterSearchDTO.getSize();

	        Specification<CommandCenter> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, commandCenterId, "commandCenterId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CommandCenter> commandCenters = this.getAllCommandCenters(spec, pageable);
		
		//System.out.println(String.valueOf(commandCenters.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(commandCenters.getTotalPages()));
		
		List<CommandCenter> commandCentersList = commandCenters.getContent();
		
		CommandCenterConvertCriteriaDTO convertCriteria = new CommandCenterConvertCriteriaDTO();
		List<CommandCenterDTO> commandCenterDTOs = this.convertCommandCentersToCommandCenterDTOs(commandCentersList,convertCriteria);
		
		CommandCenterPageDTO commandCenterPageDTO = new CommandCenterPageDTO();
		commandCenterPageDTO.setCommandCenters(commandCenterDTOs);
		commandCenterPageDTO.setTotalElements(commandCenters.getTotalElements());
		return ResponseEntity.ok(commandCenterPageDTO);
	}

	public List<CommandCenterDTO> convertCommandCentersToCommandCenterDTOs(List<CommandCenter> commandCenters, CommandCenterConvertCriteriaDTO convertCriteria) {
		
		List<CommandCenterDTO> commandCenterDTOs = new ArrayList<CommandCenterDTO>();
		
		for (CommandCenter commandCenter : commandCenters) {
			commandCenterDTOs.add(convertCommandCenterToCommandCenterDTO(commandCenter,convertCriteria));
		}
		
		return commandCenterDTOs;

	}
	
	public CommandCenterDTO convertCommandCenterToCommandCenterDTO(CommandCenter commandCenter, CommandCenterConvertCriteriaDTO convertCriteria) {
		
		CommandCenterDTO commandCenterDTO = new CommandCenterDTO();
		
		commandCenterDTO.setCommandCenterId(commandCenter.getCommandCenterId());

	
		commandCenterDTO.setName(commandCenter.getName());

	

		
		return commandCenterDTO;
	}

	public ResultDTO updateCommandCenter(CommandCenterDTO commandCenterDTO, RequestDTO requestDTO) {
		
		CommandCenter commandCenter = commandCenterDao.getById(commandCenterDTO.getCommandCenterId());

		commandCenter.setCommandCenterId(ControllerUtils.setValue(commandCenter.getCommandCenterId(), commandCenterDTO.getCommandCenterId()));

		commandCenter.setName(ControllerUtils.setValue(commandCenter.getName(), commandCenterDTO.getName()));



        commandCenter = commandCenterDao.save(commandCenter);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CommandCenterDTO getCommandCenterDTOById(Integer commandCenterId) {
	
		CommandCenter commandCenter = commandCenterDao.getById(commandCenterId);
			
		
		CommandCenterConvertCriteriaDTO convertCriteria = new CommandCenterConvertCriteriaDTO();
		return(this.convertCommandCenterToCommandCenterDTO(commandCenter,convertCriteria));
	}







}
