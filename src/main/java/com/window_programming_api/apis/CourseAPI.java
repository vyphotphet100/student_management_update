package com.window_programming_api.apis;

import com.window_programming_api.dto.CourseDTO;
import com.window_programming_api.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseAPI {

	@Autowired
	private ICourseService courseService;

	@GetMapping("/api/course")
	public ResponseEntity<CourseDTO> getCourse() {
		CourseDTO courseDto = courseService.findAll();
		return new ResponseEntity<CourseDTO>(courseDto, courseDto.getHttpStatus());
	}

	@GetMapping("/api/course/{courseId}")
	public ResponseEntity<CourseDTO> getCourseById(@PathVariable("courseId") String courseId) {
		CourseDTO courseDto = courseService.findOne(courseId);
		return new ResponseEntity<CourseDTO>(courseDto, courseDto.getHttpStatus());
	}

	@PostMapping("/api/course")
	public ResponseEntity<CourseDTO> postCourse(@RequestBody CourseDTO courseDto) {
		courseDto = courseService.save(courseDto);
		return new ResponseEntity<CourseDTO>(courseDto, courseDto.getHttpStatus());
	}

	@PutMapping("/api/course")
	public ResponseEntity<CourseDTO> putCourse(@RequestBody CourseDTO courseDto) {
		courseDto = courseService.update(courseDto);
		return new ResponseEntity<CourseDTO>(courseDto, courseDto.getHttpStatus());
	}

	@DeleteMapping("/api/course/{courseId}")
	public ResponseEntity<CourseDTO> deleteCourse(@PathVariable("courseId") String courseId) {
		CourseDTO courseDto = courseService.delete(courseId);
		return new ResponseEntity<CourseDTO>(courseDto, courseDto.getHttpStatus());
	}
}
