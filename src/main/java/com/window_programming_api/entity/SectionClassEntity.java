package com.window_programming_api.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Table(name = "section_class")
@Getter @Setter
public class SectionClassEntity extends BaseEntity{
	
	@Id
	@Column(name="id", columnDefinition = "VARCHAR(20)")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private Date startTime;
	
	@Column(name = "end_time")
	@Temporal(TemporalType.TIME)
	private Date endTime;
	
	@Column(name = "weekday")
	private Integer weekday;

	@Column(name = "exam_date")
	@Temporal(TIMESTAMP)
	private Date examDate;
	
	@Column(name = "room")
	private String room;
	
	@Column(name = "period")
	private Integer period;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne 
    @JoinColumn(name = "course_id")
    private CourseEntity course;
	
	@ManyToOne 
    @JoinColumn(name = "lecturer_id")
    private LecturerEntity lecturer;
	
	@OneToMany(mappedBy = "sectionClass")
    private List<RegisterEntity> registers = new ArrayList<RegisterEntity>();
	
}
