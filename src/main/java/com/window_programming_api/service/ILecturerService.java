package com.window_programming_api.service;

import com.window_programming_api.dto.LecturerDTO;

public interface ILecturerService extends IBaseService{
	LecturerDTO findAll();
	LecturerDTO findOne(Long id);
	LecturerDTO save(LecturerDTO lecturerDto);
	LecturerDTO update(LecturerDTO lecturerDto);
	LecturerDTO findOneByTokenCode(String token);
	LecturerDTO delete(Long id);
}

