package com.window_programming_api.service.impl;

import com.window_programming_api.dto.SectionClassDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.entity.RegisterEntity;
import com.window_programming_api.entity.SectionClassEntity;
import com.window_programming_api.entity.StudentEntity;
import com.window_programming_api.file.service.IStudentFileService;
import com.window_programming_api.jwtutils.JwtUtil;
import com.window_programming_api.repository.RegisterRepository;
import com.window_programming_api.repository.SectionClassRepository;
import com.window_programming_api.repository.StudentRepository;
import com.window_programming_api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService implements IStudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private IStudentFileService studentFileService;

	@Autowired
	private RegisterRepository registerRepo;

	@Autowired
	private SectionClassRepository sectionClassRepo;

	@Override
	public StudentDTO save(StudentDTO studentDto) {
		// check if student exists already
		if (studentRepo.findById(studentDto.getId()).orElse(null) == null) {
			studentDto.setRoleCode("STUDENT");
			String token = JwtUtil.generateToken(studentDto);
			studentDto.setTokenCode(token);

			StudentEntity studentEntity = studentRepo.save(converter.toEntity(studentDto, StudentEntity.class));
			StudentDTO resDto = converter.toDTO(studentEntity, StudentDTO.class);
			resDto.setMessage("Add student successfully.");
			return resDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "Student exists already.");
	}

	@Override
	public StudentDTO update(StudentDTO studentDto) {
		StudentEntity studentEntity = studentRepo.findById(studentDto.getId()).orElse(null);

		// check if student exists already
		if (studentEntity != null) {
			studentDto.setRoleCode(studentEntity.getRole().getCode());
			studentDto.setTokenCode(studentEntity.getTokenCode());

			studentEntity = studentRepo.save(converter.toEntity(studentDto, StudentEntity.class));
			studentDto = converter.toDTO(studentEntity, StudentDTO.class);
			studentDto.setMessage("Update student successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "Student does not exist.");
	}

	@Override
	public StudentDTO findAll() {
		StudentDTO studentDto = new StudentDTO();
		List<StudentEntity> studentEntities = studentRepo.findAll();

		if (!studentEntities.isEmpty()) {
			for (int i = 0; i < studentEntities.size(); i++)
				studentDto.getListResult().add(converter.toDTO(studentEntities.get(i), StudentDTO.class));
			studentDto.setMessage("Load student list successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "There is no student.");
	}

	@Override
	public StudentDTO findOne(String studentId) {
		StudentDTO studentDto = converter.toDTO(studentRepo.findById(studentId).orElse(null), StudentDTO.class);
		if (studentDto != null) {
			studentDto.setMessage("Get student having id = " + studentId + " successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(new StudentDTO(),
				"Student having id = " + studentId + " does not exist.");
	}

	@Override
	public StudentDTO delete(String studentId) {
		StudentDTO studentDto = new StudentDTO();

		if (studentRepo.findById(studentId).orElse(null) != null) {
			// delete register
			List<RegisterEntity> registerEntities = registerRepo.findAllByStudentId(studentId);
			for (RegisterEntity registerEntity : registerEntities)
				registerRepo.deleteById(registerEntity.getId());

			// delete student in database
			studentRepo.deleteById(studentId);

			// delete all file of student
			studentFileService.deleteAll(studentId);

			studentDto.setMessage("Delete student successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "Student having id=" + studentId + " does not exist.");
	}

	@Override
	public StudentDTO findOneByTokenCode(String token) {
		StudentDTO studentDto = new StudentDTO();
		StudentEntity studentEntity = studentRepo.findOneByTokenCode(token);

		if (studentEntity != null) {
			studentDto = this.converter.toDTO(studentEntity, StudentDTO.class);
			studentDto.setMessage("Load student successfully.");
			return studentDto;
		}

		return (StudentDTO) this.ExceptionObject(studentDto, "Cannot find student.");
	}

	@Override
	public StudentDTO findAllBySectionClassId(String sectionClassId) {
		List<RegisterEntity> registerEntities = registerRepo.findAllBySectionClassId(sectionClassId);
		StudentDTO studentDto = new StudentDTO();
		for (RegisterEntity registerEntity: registerEntities)
			studentDto.getListResult().add(converter.toDTO(registerEntity.getStudent(), StudentDTO.class));

		return studentDto;
	}

	@Override
	public StudentDTO findAllRegisteredSectionClassByStudentId(String studentId) {
		StudentDTO studentDto = new StudentDTO();

		List<RegisterEntity> registerEntities = registerRepo.findAllByStudentId(studentId);
		if (!registerEntities.isEmpty()) {
			for (RegisterEntity registerEntity : registerEntities) {
				SectionClassEntity sectionClassEntity = sectionClassRepo
						.findById(registerEntity.getSectionClass().getId()).orElse(null);
				studentDto.getListResult().add(this.converter.toDTO(sectionClassEntity, SectionClassDTO.class));
			}
			studentDto.setMessage("Get registered section class by student_id = " + studentId + " successfully.");
			return studentDto;
		}
		return (StudentDTO) this.ExceptionObject(studentDto, "This student does not have any registered section class");
	}
}
