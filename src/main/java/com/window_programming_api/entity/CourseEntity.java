package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
@Getter
@Setter
public class CourseEntity extends BaseEntity{
	
	@Id
	@Column(name="id", columnDefinition = "VARCHAR(20)")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "number_of_credit")
	private Integer numberOfCredit;
	
	@Column(name = "fee")
	private Long fee;
	
	@OneToMany(mappedBy = "course")
    private List<SectionClassEntity> sectionClasses = new ArrayList<SectionClassEntity>();

}
