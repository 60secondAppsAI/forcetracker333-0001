package com.forcetracker333.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OperationPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<OperationDTO> operations;
}




