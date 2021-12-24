package com.window_programming_api.service.impl;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.entity.EducationTrainingEntity;
import com.window_programming_api.entity.LecturerEntity;
import com.window_programming_api.entity.StudentEntity;
import com.window_programming_api.jwtutils.JwtUtil;
import com.window_programming_api.repository.EducationTrainingRepository;
import com.window_programming_api.repository.LecturerRepository;
import com.window_programming_api.repository.StudentRepository;
import com.window_programming_api.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService extends BaseService implements ILoginService {

	@Autowired
	private EducationTrainingRepository educationTrainingRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private LecturerRepository lecturerRepo;

	@Override
	public Object login(String username, String password) {
		// Check admin
		EducationTrainingEntity educationTrainingEntity = educationTrainingRepo.findOneByUsernameAndPassword(username,
				password);
		if (educationTrainingEntity != null) {
			EducationTrainingDTO educationTrainingDto = (EducationTrainingDTO) this.converter
					.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
			educationTrainingDto.setTokenCode(JwtUtil.getKeyTokenTail(educationTrainingDto.getTokenCode()));
			String newToken = JwtUtil.generateToken(educationTrainingDto);
			educationTrainingDto.setTokenCode(newToken);
			educationTrainingEntity = educationTrainingRepo
					.save(this.converter.toEntity(educationTrainingDto, EducationTrainingEntity.class));
			return this.converter.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
		}

		// check student
		StudentEntity studentEntity = studentRepo.findOneByUsernameAndPassword(username, password);
		if (studentEntity != null) {
			StudentDTO studentDto = (StudentDTO) this.converter
					.toDTO(studentEntity, StudentDTO.class);
			studentDto.setTokenCode(JwtUtil.getKeyTokenTail(studentDto.getTokenCode()));
			String newToken = JwtUtil.generateToken(studentDto);
			studentDto.setTokenCode(newToken);
			studentEntity = studentRepo
					.save(this.converter.toEntity(studentDto, StudentEntity.class));
			return this.converter.toDTO(studentEntity, StudentDTO.class);
		}

		// check lecturer
		LecturerEntity lecturerEntity = lecturerRepo.findOneByUsernameAndPassword(username, password);
		if (lecturerEntity != null) {
			LecturerDTO lecturerDto = (LecturerDTO) this.converter
					.toDTO(lecturerEntity, LecturerDTO.class);
			lecturerDto.setTokenCode(JwtUtil.getKeyTokenTail(lecturerDto.getTokenCode()));
			String newToken = JwtUtil.generateToken(lecturerDto);
			lecturerDto.setTokenCode(newToken);
			lecturerEntity = lecturerRepo
					.save(this.converter.toEntity(lecturerDto, LecturerEntity.class));
			return this.converter.toDTO(lecturerEntity, LecturerDTO.class);
		}
			

		return this.ExceptionObject(new EducationTrainingDTO(), "Invalid username or password.");
	}

}
