package com.window_programming_api.service;

import com.window_programming_api.dto.StudentDTO;

public interface IStudentService extends IBaseService{
	StudentDTO save(StudentDTO studentDto);
	StudentDTO update(StudentDTO studentDto);
	StudentDTO findAll();
	StudentDTO findAllRegisteredSectionClassByStudentId(String studentId);
	StudentDTO findOne(String studentId);
	StudentDTO delete(String studentId);
	StudentDTO findOneByTokenCode(String token);

	StudentDTO findAllBySectionClassId(String sectionClassId);
}
