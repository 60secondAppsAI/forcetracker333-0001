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
import com.forcetracker333.dao.MissionDAO;
import com.forcetracker333.domain.Mission;
import com.forcetracker333.dto.MissionDTO;
import com.forcetracker333.dto.MissionSearchDTO;
import com.forcetracker333.dto.MissionPageDTO;
import com.forcetracker333.dto.MissionConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.MissionService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class MissionServiceImpl extends GenericServiceImpl<Mission, Integer> implements MissionService {

    private final static Logger logger = LoggerFactory.getLogger(MissionServiceImpl.class);

	@Autowired
	MissionDAO missionDao;

	


	@Override
	public GenericDAO<Mission, Integer> getDAO() {
		return (GenericDAO<Mission, Integer>) missionDao;
	}
	
	public List<Mission> findAll () {
		List<Mission> missions = missionDao.findAll();
		
		return missions;	
		
	}

	public ResultDTO addMission(MissionDTO missionDTO, RequestDTO requestDTO) {

		Mission mission = new Mission();

		mission.setMissionId(missionDTO.getMissionId());


		mission.setName(missionDTO.getName());


		mission.setDescription(missionDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		mission = missionDao.save(mission);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Mission> getAllMissions(Pageable pageable) {
		return missionDao.findAll(pageable);
	}

	public Page<Mission> getAllMissions(Specification<Mission> spec, Pageable pageable) {
		return missionDao.findAll(spec, pageable);
	}

	public ResponseEntity<MissionPageDTO> getMissions(MissionSearchDTO missionSearchDTO) {
	
			Integer missionId = missionSearchDTO.getMissionId(); 
 			String name = missionSearchDTO.getName(); 
 			String description = missionSearchDTO.getDescription(); 
 			String sortBy = missionSearchDTO.getSortBy();
			String sortOrder = missionSearchDTO.getSortOrder();
			String searchQuery = missionSearchDTO.getSearchQuery();
			Integer page = missionSearchDTO.getPage();
			Integer size = missionSearchDTO.getSize();

	        Specification<Mission> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, missionId, "missionId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Mission> missions = this.getAllMissions(spec, pageable);
		
		//System.out.println(String.valueOf(missions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(missions.getTotalPages()));
		
		List<Mission> missionsList = missions.getContent();
		
		MissionConvertCriteriaDTO convertCriteria = new MissionConvertCriteriaDTO();
		List<MissionDTO> missionDTOs = this.convertMissionsToMissionDTOs(missionsList,convertCriteria);
		
		MissionPageDTO missionPageDTO = new MissionPageDTO();
		missionPageDTO.setMissions(missionDTOs);
		missionPageDTO.setTotalElements(missions.getTotalElements());
		return ResponseEntity.ok(missionPageDTO);
	}

	public List<MissionDTO> convertMissionsToMissionDTOs(List<Mission> missions, MissionConvertCriteriaDTO convertCriteria) {
		
		List<MissionDTO> missionDTOs = new ArrayList<MissionDTO>();
		
		for (Mission mission : missions) {
			missionDTOs.add(convertMissionToMissionDTO(mission,convertCriteria));
		}
		
		return missionDTOs;

	}
	
	public MissionDTO convertMissionToMissionDTO(Mission mission, MissionConvertCriteriaDTO convertCriteria) {
		
		MissionDTO missionDTO = new MissionDTO();
		
		missionDTO.setMissionId(mission.getMissionId());

	
		missionDTO.setName(mission.getName());

	
		missionDTO.setDescription(mission.getDescription());

	

		
		return missionDTO;
	}

	public ResultDTO updateMission(MissionDTO missionDTO, RequestDTO requestDTO) {
		
		Mission mission = missionDao.getById(missionDTO.getMissionId());

		mission.setMissionId(ControllerUtils.setValue(mission.getMissionId(), missionDTO.getMissionId()));

		mission.setName(ControllerUtils.setValue(mission.getName(), missionDTO.getName()));

		mission.setDescription(ControllerUtils.setValue(mission.getDescription(), missionDTO.getDescription()));



        mission = missionDao.save(mission);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MissionDTO getMissionDTOById(Integer missionId) {
	
		Mission mission = missionDao.getById(missionId);
			
		
		MissionConvertCriteriaDTO convertCriteria = new MissionConvertCriteriaDTO();
		return(this.convertMissionToMissionDTO(mission,convertCriteria));
	}







}
