package com.forcetracker333.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class AlertDTO {

	private Integer alertId;

	private String message;

	private String level;

	private DateTime dateTime;






}
