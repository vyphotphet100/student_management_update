package com.window_programming_api.repository;

import com.window_programming_api.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, String>{
	
	CourseEntity findAllByName(String name);

}
