package com.forcetracker333.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MissionPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<MissionDTO> missions;
}





