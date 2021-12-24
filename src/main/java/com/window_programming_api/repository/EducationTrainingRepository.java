package com.window_programming_api.repository;

import com.window_programming_api.entity.EducationTrainingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationTrainingRepository extends JpaRepository<EducationTrainingEntity, String>{

	EducationTrainingEntity findOneByUsernameAndPassword(String username, String password);
	EducationTrainingEntity findOneByTokenCode(String token);
}
