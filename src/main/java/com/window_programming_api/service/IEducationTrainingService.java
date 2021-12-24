package com.window_programming_api.service;

import com.window_programming_api.dto.EducationTrainingDTO;

public interface IEducationTrainingService extends IBaseService{
	EducationTrainingDTO findAll();
	EducationTrainingDTO findOne(String username);
	EducationTrainingDTO save(EducationTrainingDTO educationTrainingDto);
	EducationTrainingDTO update(EducationTrainingDTO educationTrainingDto);
	EducationTrainingDTO findOneByTokenCode(String token);
	EducationTrainingDTO delete(String username);
}

