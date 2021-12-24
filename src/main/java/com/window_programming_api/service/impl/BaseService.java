package com.window_programming_api.service.impl;

import com.window_programming_api.converter.Converter;
import com.window_programming_api.dto.AbstractDTO;
import com.window_programming_api.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class BaseService implements IBaseService {
	
	@Autowired
	protected Converter converter;

	@Override
	public AbstractDTO ExceptionObject(AbstractDTO dto, String message) {
		dto.setHttpStatus(HttpStatus.ALREADY_REPORTED);
		dto.setMessage(message);
		return dto;
	}



}
