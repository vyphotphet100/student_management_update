package com.window_programming_api.file.service;

import com.window_programming_api.dto.RegisterDTO;
import org.springframework.stereotype.Service;

@Service
public interface IRegisterFileService extends IBaseFileService {
	RegisterDTO printRegisterList();
	RegisterDTO printRegisterBySectionClass(String sectionClassId);
	RegisterDTO printRegisterBySectionClass2(String sectionClassId);
	byte[] getFile(String source);
}
