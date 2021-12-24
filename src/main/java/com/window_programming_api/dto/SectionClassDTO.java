package com.window_programming_api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class SectionClassDTO extends AbstractDTO{
	
	private String id;
	private String name;
	private Date startTime;
	private Date endTime;
	private Integer weekday;
	private Date examDate;
	private String room;
	private Integer period;
	private String description;
    private String courseId;
    private Long lecturerId;
    private List<Long> registerIds = new ArrayList<Long>();
    
}
