package com.forcetracker333.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.forcetracker333.domain.Country;
import com.forcetracker333.dto.CountryDTO;
import com.forcetracker333.dto.CountrySearchDTO;
import com.forcetracker333.dto.CountryPageDTO;
import com.forcetracker333.dto.CountryConvertCriteriaDTO;
import com.forcetracker333.service.GenericService;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CountryService extends GenericService<Country, Integer> {

	List<Country> findAll();

	ResultDTO addCountry(CountryDTO countryDTO, RequestDTO requestDTO);

	ResultDTO updateCountry(CountryDTO countryDTO, RequestDTO requestDTO);

    Page<Country> getAllCountrys(Pageable pageable);

    Page<Country> getAllCountrys(Specification<Country> spec, Pageable pageable);

	ResponseEntity<CountryPageDTO> getCountrys(CountrySearchDTO countrySearchDTO);
	
	List<CountryDTO> convertCountrysToCountryDTOs(List<Country> countrys, CountryConvertCriteriaDTO convertCriteria);

	CountryDTO getCountryDTOById(Integer countryId);







}





