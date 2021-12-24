package com.window_programming_api.file.service;

import com.window_programming_api.dto.AbstractDTO;
import org.springframework.stereotype.Service;

@Service
public interface IBaseFileService {
	AbstractDTO ExceptionObject(AbstractDTO dto, String message);
}
