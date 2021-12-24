package com.window_programming_api.repository;

import com.window_programming_api.entity.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, Long>{
	RegisterEntity findOneByStudentIdAndSectionClassId(String studentId, String sectionClassId);
	List<RegisterEntity> findAllByStudentId(String studentId);
	List<RegisterEntity> findAllBySectionClassId(String sectionClassId);
}
