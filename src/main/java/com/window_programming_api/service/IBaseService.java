package com.window_programming_api.service;

import com.window_programming_api.dto.AbstractDTO;

public interface IBaseService {
	AbstractDTO ExceptionObject(AbstractDTO dto, String message);
}
