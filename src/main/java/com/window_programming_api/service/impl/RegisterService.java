package com.window_programming_api.service.impl;

import com.window_programming_api.dto.RegisterDTO;
import com.window_programming_api.entity.RegisterEntity;
import com.window_programming_api.repository.RegisterRepository;
import com.window_programming_api.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterService extends BaseService implements IRegisterService {

	@Autowired
	private RegisterRepository registerRepo;
	
	@Override
	public RegisterDTO findAll() {
		RegisterDTO registerDto = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAll();
		
		if (!registerEntities.isEmpty()) {
			for (RegisterEntity registerEntity : registerEntities)
				registerDto.getListResult().add(this.converter.toDTO(registerEntity, RegisterDTO.class));
			
			registerDto.setMessage("Load register list successfully.");
			return registerDto;
		}
		
		return (RegisterDTO)this.ExceptionObject(registerDto, "There is no register.");
	}

	@Override
	public RegisterDTO findOne(Long id) {
		RegisterDTO registerDto = new RegisterDTO();
		RegisterEntity registerEntity = registerRepo.findById(id).orElse(null);
		
		if (registerEntity != null) {
			registerDto = this.converter.toDTO(registerEntity, RegisterDTO.class);
			registerDto.setMessage("Get register id = " + id + " successfully.");
			return registerDto;
		}
		
		return (RegisterDTO) this.ExceptionObject(registerDto, "Register does not exist.");
	}

	@Override
	public RegisterDTO save(RegisterDTO registerDto) {
		if (registerRepo.findOneByStudentIdAndSectionClassId(registerDto.getStudentId(), registerDto.getSectionClassId()) == null) {
			RegisterEntity registerEntity = this.converter.toEntity(registerDto, RegisterEntity.class);
			registerEntity = registerRepo.save(registerEntity);
			registerDto = this.converter.toDTO(registerEntity, RegisterDTO.class);
			registerDto.setMessage("Save register successfully.");
			return registerDto;
		}
		
		return (RegisterDTO) this.ExceptionObject(registerDto, "Register exists already.");
	}

	@Override
	public RegisterDTO update(RegisterDTO registerDto) {
		RegisterEntity registerEntity = registerRepo.findOneByStudentIdAndSectionClassId(registerDto.getStudentId(), registerDto.getSectionClassId());
		if (registerEntity != null) {
			registerDto.setId(registerEntity.getId());
			registerEntity = this.converter.toEntity(registerDto, RegisterEntity.class);
			registerEntity = registerRepo.save(registerEntity);
			registerDto = this.converter.toDTO(registerEntity, RegisterDTO.class);
			registerDto.setMessage("Update register successfully.");
			return registerDto;
		}
		
		return (RegisterDTO) this.ExceptionObject(registerDto, "Register does not exist.");
	}

	@Override
	public RegisterDTO delete(Long id) {
		RegisterDTO registerDto = new RegisterDTO();
		
		if (registerRepo.findById(id).orElse(null) != null) {
			registerRepo.deleteById(id);
			registerDto.setMessage("Delete register successfully.");
			return registerDto;
		}
		
		return (RegisterDTO) this.ExceptionObject(registerDto, "Register does not exist.");
	}

	@Override
	public RegisterDTO findAllBySectionClassId(String sectionClassId) {
		RegisterDTO registerDTO = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAllBySectionClassId(sectionClassId);
		for (RegisterEntity registerEntity: registerEntities)
			registerDTO.getListResult().add(converter.toDTO(registerEntity, RegisterDTO.class));

		return registerDTO;
	}

	@Override
	public RegisterDTO findAllByStudentId(String studentId) {
		RegisterDTO registerDto = new RegisterDTO();
		List<RegisterEntity> registerEntities = registerRepo.findAllByStudentId(studentId);
		
		if (!registerEntities.isEmpty()) {
			for (RegisterEntity registerEntity : registerEntities)
				registerDto.getListResult().add(this.converter.toDTO(registerEntity, RegisterDTO.class));
			registerDto.setMessage("Get register by student_id = " + studentId + " successfully.");
			return registerDto;
		}
		
		return (RegisterDTO)this.ExceptionObject(registerDto, "This student does not have any register.");
	}

}
