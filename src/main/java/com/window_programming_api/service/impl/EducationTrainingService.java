package com.window_programming_api.service.impl;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.entity.EducationTrainingEntity;
import com.window_programming_api.file.service.impl.EducationTrainingFileService;
import com.window_programming_api.jwtutils.JwtUtil;
import com.window_programming_api.repository.EducationTrainingRepository;
import com.window_programming_api.service.IEducationTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationTrainingService extends BaseService implements IEducationTrainingService {

	@Autowired
	private EducationTrainingRepository educationTrainingRepo;
	
	@Autowired
	private EducationTrainingFileService educationFileService;

	@Override
	public EducationTrainingDTO findAll() {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		List<EducationTrainingEntity> educationTrainingEntities = educationTrainingRepo.findAll();
		if (!educationTrainingEntities.isEmpty()) {
			for (int i = 0; i < educationTrainingEntities.size(); i++)
				educationTrainingDto.getListResult()
						.add(this.converter.toDTO(educationTrainingEntities.get(i), EducationTrainingDTO.class));

			educationTrainingDto.setMessage("Load education training list successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "There is no education training.");
	}

	@Override
	public EducationTrainingDTO update(EducationTrainingDTO educationTrainingDto) {

		// check if student exists already
		EducationTrainingEntity educationTrainingEntity = educationTrainingRepo
				.findById(educationTrainingDto.getUsername()).orElse(null);
		if (educationTrainingEntity != null) {
			educationTrainingDto.setTokenCode(educationTrainingEntity.getTokenCode());
			educationTrainingDto.setRoleCode(educationTrainingEntity.getRole().getCode());
			educationTrainingEntity = educationTrainingRepo
					.save(converter.toEntity(educationTrainingDto, EducationTrainingEntity.class));
			educationTrainingDto = converter.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
			educationTrainingDto.setMessage("Update education training successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This user does not exist.");
	}

	@Override
	public EducationTrainingDTO findOneByTokenCode(String token) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		EducationTrainingEntity educationTrainingEntity = educationTrainingRepo.findOneByTokenCode(token);

		if (educationTrainingEntity != null) {
			educationTrainingDto = this.converter.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
			educationTrainingDto.setMessage("Load education training successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "Cannot find education training.");
	}

	@Override
	public EducationTrainingDTO save(EducationTrainingDTO educationTrainingDto) {
		// check if student exists already
		if (educationTrainingRepo.findById(educationTrainingDto.getUsername()).orElse(null) == null) {
			String token = JwtUtil.generateToken(educationTrainingDto);
			educationTrainingDto.setTokenCode(token);
			//educationTrainingDto.setRoleCode("ADMIN");
			EducationTrainingEntity educationTrainingEntity = educationTrainingRepo
					.save(converter.toEntity(educationTrainingDto, EducationTrainingEntity.class));
			educationTrainingDto = converter.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
			educationTrainingDto.setMessage("Add education training successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This user exists already.");
	}

	@Override
	public EducationTrainingDTO delete(String username) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		// check if student exists already
		if (educationTrainingRepo.findById(username).orElse(null) != null) {
			educationTrainingRepo.deleteById(username);
			
			//delete file 
			educationFileService.deleteAll(username);
			
			educationTrainingDto.setMessage("Delete " + username + " successfully.");
			return educationTrainingDto;
		}
		
		

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This user does not exist.");
	}

	@Override
	public EducationTrainingDTO findOne(String username) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		EducationTrainingEntity educationTrainingEntity = educationTrainingRepo.findById(username).orElse(null);
		// check if student exists already
		if (educationTrainingEntity != null) {
			educationTrainingDto = this.converter.toDTO(educationTrainingEntity, EducationTrainingDTO.class);
			educationTrainingDto.setMessage("Get " + username + " successfully.");
			return educationTrainingDto;
		}

		return (EducationTrainingDTO) this.ExceptionObject(educationTrainingDto, "This user does not exist.");
	}

}
