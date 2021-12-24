package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.CourseDTO;
import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.file.service.ICourseFileService;
import com.window_programming_api.utils.MyFileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Base64;

@Service
public class CourseFileService extends BaseFileService implements ICourseFileService {

	@Override
	public CourseDTO findAll() {
		return null;
	}

	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/course/")[1];
		File rootPath = new File(
				MyFileUtil.removeDoubleSlash("src/main/resources/sources/course/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}
	
	@Override
	public CourseDTO findOneByName(String fileName) {
		CourseDTO courseDto = new CourseDTO();
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/course/" + fileName));
		try {
			byte[] fileByte = MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
			MyFileDTO fileDto = new MyFileDTO();
			fileDto.setFileName(fileName.split("\\.")[0]);
			fileDto.setFileType(fileName.split("\\.")[1]);
			fileDto.setBase64String(Base64.getEncoder().encodeToString(fileByte));
			
			courseDto.getListResult().add(fileDto);
			courseDto.setMessage("Load file to baseString64 successfully.");
			return courseDto;
		}
		catch(Exception ex) {
			return (CourseDTO)this.ExceptionObject(courseDto, "File is not found.");
		}

	}

	@Override
	public CourseDTO save(MyFileDTO fileDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDTO update(MyFileDTO fileDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CourseDTO delete(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
}
