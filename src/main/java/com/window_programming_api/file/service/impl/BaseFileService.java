package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.AbstractDTO;
import com.window_programming_api.file.service.IBaseFileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BaseFileService implements IBaseFileService{

	@Override
	public AbstractDTO ExceptionObject(AbstractDTO dto, String message) {
		dto.setHttpStatus(HttpStatus.ALREADY_REPORTED);
		dto.setMessage(message);
		return dto;
	}

}
