package com.window_programming_api.service;

import com.window_programming_api.dto.SectionClassDTO;

public interface ISectionClassService extends IBaseService{
	SectionClassDTO findOne(String id);
	SectionClassDTO findAllByLecturerId(Long lecturerId);
	SectionClassDTO findAll();
	SectionClassDTO save(SectionClassDTO sectionClassDto);
	SectionClassDTO update(SectionClassDTO sectionClassDto);
	SectionClassDTO delete(String id);
}

