package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "register")
@Getter @Setter
public class RegisterEntity extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne 
    @JoinColumn(name = "student_id", columnDefinition = "VARCHAR(20)")
    private StudentEntity student;
	
	@ManyToOne 
    @JoinColumn(name = "section_class_id")
    private SectionClassEntity sectionClass;
	
	@Column(name = "midterm_mark")
	private double midTermMark;
	
	@Column(name = "endterm_mark")
	private double endTermMark;
	
	@Column(name = "description")
	private String description;
	
}
