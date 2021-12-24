package com.window_programming_api.service;

import com.window_programming_api.dto.RoleDTO;

public interface IRoleService extends IBaseService{
	RoleDTO findOne(String code);
	RoleDTO findAll();
	RoleDTO save(RoleDTO roleDto);
	RoleDTO update(RoleDTO roleDto);
	RoleDTO delete(String code);
}
