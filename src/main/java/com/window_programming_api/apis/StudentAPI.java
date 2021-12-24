package com.window_programming_api.apis;

import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentAPI {

	@Autowired
	private IStudentService studentService;

	@PostMapping("/api/student")
	public ResponseEntity<StudentDTO> postStudent(@RequestBody StudentDTO requestDto) {
		StudentDTO studentDto = studentService.save(requestDto);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@PutMapping("/api/student")
	public ResponseEntity<StudentDTO> putStudent(@RequestBody StudentDTO requestDto) {
		StudentDTO studentDto = studentService.update(requestDto);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@GetMapping("/api/student")
	public ResponseEntity<StudentDTO> getStudent() {
		StudentDTO studentDto = studentService.findAll();
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@GetMapping("/api/student/{studentId}")
	// @PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<StudentDTO> getStudentById(@PathVariable("studentId") String studentId) {
		StudentDTO studentDto = studentService.findOne(studentId);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@DeleteMapping("/api/student/{studentId}")
	public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable("studentId") String studentId) {
		StudentDTO studentDto = studentService.delete(studentId);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@GetMapping("/api/student/{studentId}/registered_section_class")
	public ResponseEntity<StudentDTO> getRegisteredSectionClass(@PathVariable("studentId") String studentId) {
		StudentDTO studentDto = studentService.findAllRegisteredSectionClassByStudentId(studentId);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@GetMapping("/api/student/section_class_id/{sectionClassId}")
	public ResponseEntity<StudentDTO> findAllBySectionClassId(@PathVariable("sectionClassId") String sectionClassId) {
		StudentDTO studentDto = studentService.findAllBySectionClassId(sectionClassId);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

}
