package com.window_programming_api.repository;

import com.window_programming_api.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
	//RoleEntity findOne(String code);
	
}
