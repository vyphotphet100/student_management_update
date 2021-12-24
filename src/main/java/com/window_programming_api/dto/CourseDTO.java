package com.window_programming_api.dto;

import java.util.ArrayList;
import java.util.List;

public class CourseDTO extends AbstractDTO{
	
	private String id;
	private String name;
	private Integer numberOfCredit;
	private Long fee;
    private List<String> sectionClassIds = new ArrayList<String>();
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfCredit() {
		return numberOfCredit;
	}
	public void setNumberOfCredit(Integer numberOfCredit) {
		this.numberOfCredit = numberOfCredit;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public List<String> getSectionClassIds() {
		return sectionClassIds;
	}
	public void setSectionClassIds(List<String> sectionClassIds) {
		this.sectionClassIds = sectionClassIds;
	}
	
}
