package com.window_programming_api.service.impl;

import com.window_programming_api.dto.RoleDTO;
import com.window_programming_api.entity.RoleEntity;
import com.window_programming_api.repository.RoleRepository;
import com.window_programming_api.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService extends BaseService implements IRoleService{
	
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public RoleDTO findOne(String code) {
		RoleDTO roleDto = new RoleDTO();
		RoleEntity roleEntity = roleRepo.findById(code).orElse(null);
		
		if (roleEntity != null) {
			roleDto = this.converter.toDTO(roleEntity, RoleDTO.class);
			roleDto.setMessage("Get role successfully.");
			return roleDto;
		}
			
		return (RoleDTO)this.ExceptionObject(roleDto, "Role does not exist.");
	}

	@Override
	public RoleDTO findAll() {
		RoleDTO roleDto = new RoleDTO();
		
		List<RoleEntity> roleEntities = roleRepo.findAll();
		if (!roleEntities.isEmpty()) {
			for (RoleEntity roleEntity : roleEntities) 
				roleDto.getListResult().add(this.converter.toDTO(roleEntity, RoleDTO.class));
			
			roleDto.setMessage("Load role list successfully.");
			return roleDto;
		}
		
		return (RoleDTO)this.ExceptionObject(roleDto, "There is no role.");
	}

	@Override
	public RoleDTO save(RoleDTO roleDto) {
		
		if (roleRepo.findById(roleDto.getCode()).orElse(null) == null) {
			RoleEntity roleEntity = roleRepo.save(this.converter.toEntity(roleDto, RoleEntity.class));
			roleDto = this.converter.toDTO(roleEntity, RoleDTO.class);
			roleDto.setMessage("Save role successfully.");
			return roleDto;
		}
		
		return (RoleDTO) this.ExceptionObject(roleDto, "This role exists already.");
	}

	@Override
	public RoleDTO update(RoleDTO roleDto) {
		if (roleRepo.findById(roleDto.getCode()).orElse(null) != null) {
			RoleEntity roleEntity = roleRepo.save(this.converter.toEntity(roleDto, RoleEntity.class));
			roleDto = this.converter.toDTO(roleEntity, RoleDTO.class);
			roleDto.setMessage("Update role successfully.");
			return roleDto;
		}
		
		return (RoleDTO) this.ExceptionObject(roleDto, "This role does not exist.");
	}

	@Override
	public RoleDTO delete(String code) {
		RoleDTO roleDto = new RoleDTO();
		if (roleRepo.findById(code).orElse(null) != null) {
			roleRepo.deleteById(code);
			roleDto.setMessage("Delete role successfully.");
			return roleDto;
		}
		
		return (RoleDTO) this.ExceptionObject(roleDto, "This role does not exist.");
	}

}
