package com.window_programming_api.file.service;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.MyFileDTO;
import org.springframework.stereotype.Service;

@Service
public interface IEducationTrainingFileService extends IBaseFileService{
	EducationTrainingDTO findOneByFileName(String fileName);
	EducationTrainingDTO save(MyFileDTO fileDto);
	EducationTrainingDTO update(MyFileDTO fileDto);
	EducationTrainingDTO delete(MyFileDTO fileDto);
	EducationTrainingDTO deleteAll(String username);
	
	byte[] getFile(String source);
}
