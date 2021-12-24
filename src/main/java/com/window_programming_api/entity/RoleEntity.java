package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter @Setter
public class RoleEntity extends BaseEntity{
	
	@Id
	@Column(name="code", columnDefinition = "VARCHAR(20)")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "role")
    private List<StudentEntity> students;

	@OneToMany(mappedBy = "role")
    private List<EducationTrainingEntity> educationTrainings = new ArrayList<EducationTrainingEntity>();
	
	@OneToMany(mappedBy = "role")
    private List<LecturerEntity> lecturers;
	
}
