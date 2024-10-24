package com.forcetracker333.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LocationPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<LocationDTO> locations;
}





