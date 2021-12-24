package com.window_programming_api.service.impl;

import com.window_programming_api.dto.SectionClassDTO;
import com.window_programming_api.entity.SectionClassEntity;
import com.window_programming_api.repository.SectionClassRepository;
import com.window_programming_api.service.ISectionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionClassService extends BaseService implements ISectionClassService{

	@Autowired
	private SectionClassRepository sectionClassRepo;
	
	@Override
	public SectionClassDTO findAll() {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		List<SectionClassEntity> sectionClassEntities = sectionClassRepo.findAll();
		
		if (!sectionClassEntities.isEmpty()) {
			for (SectionClassEntity sectionClassEntity : sectionClassEntities)
				sectionClassDto.getListResult().add(this.converter.toDTO(sectionClassEntity, SectionClassDTO.class));
			
			sectionClassDto.setMessage("Load section class list successfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO)this.ExceptionObject(sectionClassDto, "There is no section class.");
	}

	@Override
	public SectionClassDTO findOne(String id) {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		SectionClassEntity sectionClassEntity = sectionClassRepo.findById(id).orElse(null);
		
		if (sectionClassEntity != null) {
			sectionClassDto = this.converter.toDTO(sectionClassEntity, SectionClassDTO.class);
			sectionClassDto.setMessage("Get section class having id = " + id + " successfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO) this.ExceptionObject(sectionClassDto, "This section class exists already.");
	}

	@Override
	public SectionClassDTO save(SectionClassDTO sectionClassDto) {
		if (sectionClassRepo.findById(sectionClassDto.getId()).orElse(null) == null) {
			SectionClassEntity sectionClassEntity = sectionClassRepo.save(this.converter.toEntity(sectionClassDto, SectionClassEntity.class));
			sectionClassDto = this.converter.toDTO(sectionClassEntity, SectionClassDTO.class);
			sectionClassDto.setMessage("Save section class successfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO) this.ExceptionObject(sectionClassDto, "This section class exists already.");
	}

	@Override
	public SectionClassDTO update(SectionClassDTO sectionClassDto) {
		if (sectionClassRepo.findById(sectionClassDto.getId()).orElse(null) != null) {
			SectionClassEntity sectionClassEntity = sectionClassRepo.save(this.converter.toEntity(sectionClassDto, SectionClassEntity.class));
			sectionClassDto = this.converter.toDTO(sectionClassEntity, SectionClassDTO.class);
			sectionClassDto.setMessage("Update section class successfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO) this.ExceptionObject(sectionClassDto, "This section class does not exist.");
	}

	@Override
	public SectionClassDTO delete(String id) {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		
		if (sectionClassRepo.findById(id).orElse(null) != null) {
			sectionClassRepo.deleteById(id);
			sectionClassDto.setMessage("Delete section class successfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO) this.ExceptionObject(sectionClassDto, "This section class does not exist.");
	}

	@Override
	public SectionClassDTO findAllByLecturerId(Long lecturerId) {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		List<SectionClassEntity> sectionClassEntities = sectionClassRepo.findAllByLecturerId(lecturerId);
		
		if (!sectionClassEntities.isEmpty()) {
			for (SectionClassEntity sectionClassEntity : sectionClassEntities)
				sectionClassDto.getListResult().add(this.converter.toDTO(sectionClassEntity, SectionClassDTO.class));
			sectionClassDto.setMessage("Get section class by lecturer_id = " + lecturerId + " succesfully.");
			return sectionClassDto;
		}
		
		return (SectionClassDTO)this.ExceptionObject(sectionClassDto, "This lecturer does not have any section class.");
		
	}

}
