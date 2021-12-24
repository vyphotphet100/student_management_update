package com.window_programming_api.repository;

import com.window_programming_api.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, String>{
	//StudentEntity findOne(String id);
	StudentEntity findOneByUsernameAndPassword(String username, String password);
	StudentEntity findOneByTokenCode(String token);
}
