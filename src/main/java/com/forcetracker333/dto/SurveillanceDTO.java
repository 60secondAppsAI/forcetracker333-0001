package com.forcetracker333.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class SurveillanceDTO {

	private Integer surveillanceId;

	private String areaMonitored;

	private Date startDate;

	private Date endDate;






}
