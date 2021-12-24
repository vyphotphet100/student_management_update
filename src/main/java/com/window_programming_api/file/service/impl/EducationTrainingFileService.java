package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.file.service.IEducationTrainingFileService;
import com.window_programming_api.utils.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class EducationTrainingFileService extends BaseFileService implements IEducationTrainingFileService {

	@Override
	public EducationTrainingDTO findOneByFileName(String fileName) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/education_training/" + fileName));
		try {
			byte[] fileByte = MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
			MyFileDTO fileDto = new MyFileDTO();
			fileDto.setFileName(fileName.split("\\.")[0]);
			fileDto.setFileType(fileName.split("\\.")[1]);
			fileDto.setBase64String(Base64.getEncoder().encodeToString(fileByte));

			educationTrainingDto.getListResult().add(fileDto);
			educationTrainingDto.setMessage("Load file to base64String successfully.");
			return educationTrainingDto;
		} catch (Exception ex) {
			return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "File is not found.");
		}
	}

	@Override
	public EducationTrainingDTO save(MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/education_training/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (!filePath.exists()) {
			fileDto.setFileName("education_training/" + fileDto.getFileName());
			educationTrainingDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			educationTrainingDto.setMessage("Upload file successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This file exists already.");
	}

	@Override
	public EducationTrainingDTO update(MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/education_training/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (filePath.exists()) {
			fileDto.setFileName("education_training/" + fileDto.getFileName());
			educationTrainingDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			educationTrainingDto.setMessage("Update file successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This file does not exists.");
	}

	@Override
	public EducationTrainingDTO delete(MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/education_training/" + fileDto.getFileName() + "." + fileDto.getFileType()));

		if (filePath.exists()) {
			try {
				FileUtils.forceDelete(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			educationTrainingDto.setMessage("Delete file successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "File does not exist.");
	}

	@Override
	public EducationTrainingDTO deleteAll(String username) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();

		File educationTrainingDir = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/education_training/" + username));
		try {
			FileUtils.deleteDirectory(educationTrainingDir);
			educationTrainingDto.setMessage("Delete all file(s) of education training successfully.");
			return educationTrainingDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "Delete all file(s) of education training fail.");
	}

	@Override
	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/education_training/")[1];
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/education_training/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}

}
