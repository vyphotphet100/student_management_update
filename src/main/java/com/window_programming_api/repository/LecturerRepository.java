package com.window_programming_api.repository;

import com.window_programming_api.entity.LecturerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerRepository extends JpaRepository<LecturerEntity, Long>{
	LecturerEntity findOneByUsernameAndPassword(String username, String password);
	LecturerEntity findOneByTokenCode(String token);
	LecturerEntity findOneByUsername(String username);
}
