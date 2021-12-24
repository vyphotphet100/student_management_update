package com.window_programming_api.file.service.impl;

import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.file.service.ILecturerFileService;
import com.window_programming_api.utils.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@Service
public class LecturerFileService extends BaseFileService implements ILecturerFileService{

	@Override
	public LecturerDTO findOneByFileName(String fileName) {
		LecturerDTO lecturerDto = new LecturerDTO();
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/lecturer/" + fileName));
		try {
			byte[] fileByte = MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
			MyFileDTO fileDto = new MyFileDTO();
			fileDto.setFileName(fileName.split("\\.")[0]);
			fileDto.setFileType(fileName.split("\\.")[1]);
			fileDto.setBase64String(Base64.getEncoder().encodeToString(fileByte));

			lecturerDto.getListResult().add(fileDto);
			lecturerDto.setMessage("Load file to base64String successfully.");
			return lecturerDto;
		} catch (Exception ex) {
			return (LecturerDTO) this.ExceptionObject(lecturerDto, "File is not found.");
		}
	}

	@Override
	public LecturerDTO save(MyFileDTO fileDto) {
		LecturerDTO lecturerDto = new LecturerDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/lecturer/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (!filePath.exists()) {
			fileDto.setFileName("lecturer/" + fileDto.getFileName());
			lecturerDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			lecturerDto.setMessage("Upload file successfully.");
			return lecturerDto;
		}

		return (LecturerDTO) this.ExceptionObject(lecturerDto, "This file exists already.");
	}

	@Override
	public LecturerDTO update(MyFileDTO fileDto) {
		LecturerDTO lecturerDto = new LecturerDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/lecturer/" + fileDto.getFileName() + "." + fileDto.getFileType()));
		if (filePath.exists()) {
			fileDto.setFileName("lecturer/" + fileDto.getFileName());
			lecturerDto.getListResult().add(
					MyFileUtil.upFile(fileDto.getFileName() + "." + fileDto.getFileType(), fileDto.getBase64String()));
			lecturerDto.setMessage("Update file successfully.");
			return lecturerDto;
		}

		return (LecturerDTO) this.ExceptionObject(lecturerDto, "This file does not exists.");
	}

	@Override
	public LecturerDTO delete(MyFileDTO fileDto) {
		LecturerDTO lecturerDto = new LecturerDTO();

		File filePath = new File(MyFileUtil.removeDoubleSlash(
				"src/main/resources/sources/lecturer/" + fileDto.getFileName() + "." + fileDto.getFileType()));

		if (filePath.exists()) {
			try {
				FileUtils.forceDelete(filePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lecturerDto.setMessage("Delete file successfully.");
			return lecturerDto;
		}

		return (LecturerDTO) this.ExceptionObject(lecturerDto, "File does not exist.");
	}

	@Override
	public LecturerDTO deleteAll(long id) {
		LecturerDTO lecturerDto = new LecturerDTO();

		File educationTrainingDir = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/lecturer/" + id));
		try {
			FileUtils.deleteDirectory(educationTrainingDir);
			lecturerDto.setMessage("Delete all file(s) of lecturer successfully.");
			return lecturerDto;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (LecturerDTO) this.ExceptionObject(lecturerDto, "Delete all file(s) of lecturer fail.");
	}

	@Override
	public byte[] getFile(String source) {
		String fileName = source.split("/api/file/lecturer/")[1];
		File rootPath = new File(MyFileUtil.removeDoubleSlash("src/main/resources/sources/lecturer/" + fileName));
		return MyFileUtil.getByteBySource(rootPath.getAbsolutePath());
	}

	
}
