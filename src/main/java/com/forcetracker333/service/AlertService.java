package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Alert;
import com.forcetracker333.dto.AlertDTO;
import com.forcetracker333.dto.AlertSearchDTO;
import com.forcetracker333.dto.AlertPageDTO;
import com.forcetracker333.dto.AlertConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AlertService extends GenericService<Alert, Integer> {

	List<Alert> findAll();

	ResultDTO addAlert(AlertDTO alertDTO, RequestDTO requestDTO);

	ResultDTO updateAlert(AlertDTO alertDTO, RequestDTO requestDTO);

    Page<Alert> getAllAlerts(Pageable pageable);

    Page<Alert> getAllAlerts(Specification<Alert> spec, Pageable pageable);

	ResponseEntity<AlertPageDTO> getAlerts(AlertSearchDTO alertSearchDTO);
	
	List<AlertDTO> convertAlertsToAlertDTOs(List<Alert> alerts, AlertConvertCriteriaDTO convertCriteria);

	AlertDTO getAlertDTOById(Integer alertId);







}





