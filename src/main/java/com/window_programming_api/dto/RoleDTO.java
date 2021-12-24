package com.window_programming_api.dto;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends AbstractDTO{
	
	private String name;
	private String code;
	private List<String> studentIds = new ArrayList<String>();
	private List<Long> lecturerIds = new ArrayList<Long>();
	private List<String> educationTrainingUsernames = new ArrayList<String>();
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<String> getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(List<String> studentIds) {
		this.studentIds = studentIds;
	}
	public List<Long> getLecturerIds() {
		return lecturerIds;
	}
	public void setLecturerIds(List<Long> lecturerIds) {
		this.lecturerIds = lecturerIds;
	}
	public List<String> getEducationTrainingUsernames() {
		return educationTrainingUsernames;
	}
	public void setEducationTrainingUsernames(List<String> educationTrainingUsernames) {
		this.educationTrainingUsernames = educationTrainingUsernames;
	}
	
}
