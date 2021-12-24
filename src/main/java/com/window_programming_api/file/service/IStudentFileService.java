package com.window_programming_api.file.service;

import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.dto.StudentDTO;
import org.springframework.stereotype.Service;

@Service
public interface IStudentFileService extends IBaseFileService{
	byte[] getFile(String source);
	StudentDTO findOneByFileName(String fileName);
	StudentDTO findAll();
	StudentDTO save(MyFileDTO fileDto);
	StudentDTO update(MyFileDTO fileDto);
	StudentDTO delete(MyFileDTO fileDto);
	StudentDTO deleteAll(String studentId);
	
	StudentDTO printStudentList();
	StudentDTO printResult(String studentId);
	StudentDTO printTimetable(String studentId);
}
