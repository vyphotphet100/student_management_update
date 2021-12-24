package com.window_programming_api.repository;

import com.window_programming_api.entity.SectionClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SectionClassRepository extends JpaRepository<SectionClassEntity, String>{
	List<SectionClassEntity> findAllByLecturerId(Long lecturerId);
}
