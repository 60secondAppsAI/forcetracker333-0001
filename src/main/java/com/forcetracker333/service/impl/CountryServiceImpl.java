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
import com.forcetracker333.dao.CountryDAO;
import com.forcetracker333.domain.Country;
import com.forcetracker333.dto.CountryDTO;
import com.forcetracker333.dto.CountrySearchDTO;
import com.forcetracker333.dto.CountryPageDTO;
import com.forcetracker333.dto.CountryConvertCriteriaDTO;
import com.forcetracker333.dto.common.RequestDTO;
import com.forcetracker333.dto.common.ResultDTO;
import com.forcetracker333.service.CountryService;
import com.forcetracker333.util.ControllerUtils;





@Service
public class CountryServiceImpl extends GenericServiceImpl<Country, Integer> implements CountryService {

    private final static Logger logger = LoggerFactory.getLogger(CountryServiceImpl.class);

	@Autowired
	CountryDAO countryDao;

	


	@Override
	public GenericDAO<Country, Integer> getDAO() {
		return (GenericDAO<Country, Integer>) countryDao;
	}
	
	public List<Country> findAll () {
		List<Country> countrys = countryDao.findAll();
		
		return countrys;	
		
	}

	public ResultDTO addCountry(CountryDTO countryDTO, RequestDTO requestDTO) {

		Country country = new Country();

		country.setCountryId(countryDTO.getCountryId());


		country.setName(countryDTO.getName());


		country.setCode(countryDTO.getCode());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		country = countryDao.save(country);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Country> getAllCountrys(Pageable pageable) {
		return countryDao.findAll(pageable);
	}

	public Page<Country> getAllCountrys(Specification<Country> spec, Pageable pageable) {
		return countryDao.findAll(spec, pageable);
	}

	public ResponseEntity<CountryPageDTO> getCountrys(CountrySearchDTO countrySearchDTO) {
	
			Integer countryId = countrySearchDTO.getCountryId(); 
 			String name = countrySearchDTO.getName(); 
 			String code = countrySearchDTO.getCode(); 
 			String sortBy = countrySearchDTO.getSortBy();
			String sortOrder = countrySearchDTO.getSortOrder();
			String searchQuery = countrySearchDTO.getSearchQuery();
			Integer page = countrySearchDTO.getPage();
			Integer size = countrySearchDTO.getSize();

	        Specification<Country> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, countryId, "countryId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, code, "code"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("code")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Country> countrys = this.getAllCountrys(spec, pageable);
		
		//System.out.println(String.valueOf(countrys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(countrys.getTotalPages()));
		
		List<Country> countrysList = countrys.getContent();
		
		CountryConvertCriteriaDTO convertCriteria = new CountryConvertCriteriaDTO();
		List<CountryDTO> countryDTOs = this.convertCountrysToCountryDTOs(countrysList,convertCriteria);
		
		CountryPageDTO countryPageDTO = new CountryPageDTO();
		countryPageDTO.setCountrys(countryDTOs);
		countryPageDTO.setTotalElements(countrys.getTotalElements());
		return ResponseEntity.ok(countryPageDTO);
	}

	public List<CountryDTO> convertCountrysToCountryDTOs(List<Country> countrys, CountryConvertCriteriaDTO convertCriteria) {
		
		List<CountryDTO> countryDTOs = new ArrayList<CountryDTO>();
		
		for (Country country : countrys) {
			countryDTOs.add(convertCountryToCountryDTO(country,convertCriteria));
		}
		
		return countryDTOs;

	}
	
	public CountryDTO convertCountryToCountryDTO(Country country, CountryConvertCriteriaDTO convertCriteria) {
		
		CountryDTO countryDTO = new CountryDTO();
		
		countryDTO.setCountryId(country.getCountryId());

	
		countryDTO.setName(country.getName());

	
		countryDTO.setCode(country.getCode());

	

		
		return countryDTO;
	}

	public ResultDTO updateCountry(CountryDTO countryDTO, RequestDTO requestDTO) {
		
		Country country = countryDao.getById(countryDTO.getCountryId());

		country.setCountryId(ControllerUtils.setValue(country.getCountryId(), countryDTO.getCountryId()));

		country.setName(ControllerUtils.setValue(country.getName(), countryDTO.getName()));

		country.setCode(ControllerUtils.setValue(country.getCode(), countryDTO.getCode()));



        country = countryDao.save(country);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CountryDTO getCountryDTOById(Integer countryId) {
	
		Country country = countryDao.getById(countryId);
			
		
		CountryConvertCriteriaDTO convertCriteria = new CountryConvertCriteriaDTO();
		return(this.convertCountryToCountryDTO(country,convertCriteria));
	}







}
