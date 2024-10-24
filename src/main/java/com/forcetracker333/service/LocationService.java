package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Location;
import com.forcetracker333.dto.LocationDTO;
import com.forcetracker333.dto.LocationSearchDTO;
import com.forcetracker333.dto.LocationPageDTO;
import com.forcetracker333.dto.LocationConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LocationService extends GenericService<Location, Integer> {

	List<Location> findAll();

	ResultDTO addLocation(LocationDTO locationDTO, RequestDTO requestDTO);

	ResultDTO updateLocation(LocationDTO locationDTO, RequestDTO requestDTO);

    Page<Location> getAllLocations(Pageable pageable);

    Page<Location> getAllLocations(Specification<Location> spec, Pageable pageable);

	ResponseEntity<LocationPageDTO> getLocations(LocationSearchDTO locationSearchDTO);
	
	List<LocationDTO> convertLocationsToLocationDTOs(List<Location> locations, LocationConvertCriteriaDTO convertCriteria);

	LocationDTO getLocationDTOById(Integer locationId);







}





