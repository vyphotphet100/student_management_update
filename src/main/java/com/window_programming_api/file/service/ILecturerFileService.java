package com.window_programming_api.file.service;

import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.MyFileDTO;
import org.springframework.stereotype.Service;

@Service
public interface ILecturerFileService extends IBaseFileService {
	LecturerDTO findOneByFileName(String fileName);
	LecturerDTO save(MyFileDTO fileDto);
	LecturerDTO update(MyFileDTO fileDto);
	LecturerDTO delete(MyFileDTO fileDto);
	LecturerDTO deleteAll(long id);

	byte[] getFile(String source);
}
