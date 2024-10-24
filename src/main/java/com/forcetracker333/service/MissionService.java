package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Mission;
import com.forcetracker333.dto.MissionDTO;
import com.forcetracker333.dto.MissionSearchDTO;
import com.forcetracker333.dto.MissionPageDTO;
import com.forcetracker333.dto.MissionConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MissionService extends GenericService<Mission, Integer> {

	List<Mission> findAll();

	ResultDTO addMission(MissionDTO missionDTO, RequestDTO requestDTO);

	ResultDTO updateMission(MissionDTO missionDTO, RequestDTO requestDTO);

    Page<Mission> getAllMissions(Pageable pageable);

    Page<Mission> getAllMissions(Specification<Mission> spec, Pageable pageable);

	ResponseEntity<MissionPageDTO> getMissions(MissionSearchDTO missionSearchDTO);
	
	List<MissionDTO> convertMissionsToMissionDTOs(List<Mission> missions, MissionConvertCriteriaDTO convertCriteria);

	MissionDTO getMissionDTOById(Integer missionId);







}





