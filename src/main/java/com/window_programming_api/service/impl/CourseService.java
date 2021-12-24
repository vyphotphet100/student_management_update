package com.window_programming_api.service.impl;

import com.window_programming_api.dto.CourseDTO;
import com.window_programming_api.entity.CourseEntity;
import com.window_programming_api.repository.CourseRepository;
import com.window_programming_api.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService extends BaseService implements ICourseService {

	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public CourseDTO findAll() {
		CourseDTO courseDto = new CourseDTO();
		List<CourseEntity> courseEntities = courseRepo.findAll();
		
		if (!courseEntities.isEmpty()) {
			for (int i = 0; i < courseEntities.size(); i++)
				courseDto.getListResult().add(converter.toDTO(courseEntities.get(i), CourseDTO.class));
			courseDto.setMessage("Load list of course successfully.");
			return courseDto;
		}
		
		return (CourseDTO)this.ExceptionObject(courseDto, "There is no course.");
	}
 
	@Override
	public CourseDTO findOne(String courseId) {
		CourseDTO courseDto = converter.toDTO(courseRepo.findById(courseId).orElse(null), CourseDTO.class);
		if (courseDto != null) {
			courseDto.setMessage("Load course successfully.");
			return courseDto;
		}
		
		return (CourseDTO) this.ExceptionObject(courseDto, "There is no course having courseId=" + courseId + ".");
	}

	@Override
	public CourseDTO save(CourseDTO courseDto) {
		if (courseRepo.findById(courseDto.getId()).orElse(null) == null) {
			CourseEntity courseEntity = converter.toEntity(courseDto, CourseEntity.class);
			courseDto = converter.toDTO(courseRepo.save(courseEntity), CourseDTO.class);
			courseDto.setMessage("Add course successfully.");
			return courseDto;
		}
		
		return (CourseDTO) this.ExceptionObject(courseDto, "This course id exists already.");
	}

	@Override
	public CourseDTO update(CourseDTO courseDto) {
		if (courseRepo.findById(courseDto.getId()).orElse(null) != null) {
			CourseEntity courseEntity = converter.toEntity(courseDto, CourseEntity.class);
			courseDto = converter.toDTO(courseRepo.save(courseEntity), CourseDTO.class);
			courseDto.setMessage("Update course successfully.");
			return courseDto;
		}
		
		return (CourseDTO) this.ExceptionObject(courseDto, "This course id does not exist.");
	}

	@Override
	public CourseDTO delete(String courseId) {
		CourseDTO courseDto = new CourseDTO();
		if (courseRepo.findById(courseId).orElse(null) != null) {
			courseRepo.deleteById(courseId);
			courseDto.setMessage("Delete course successfully.");
			return courseDto;
		}
		
		return (CourseDTO) this.ExceptionObject(courseDto, "This course id does not exist.");
	}

}
