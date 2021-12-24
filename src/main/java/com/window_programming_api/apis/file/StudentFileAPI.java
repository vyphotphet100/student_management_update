package com.window_programming_api.apis.file;

import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.file.service.IStudentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class StudentFileAPI {
	@Autowired
	private IStudentFileService studentFileService;

	@GetMapping("/api/file/student")
	public ResponseEntity<StudentDTO> getStudentFile(@RequestParam(value = "option", required = false) String option) {
		StudentDTO studentDto = new StudentDTO();
		if (option == null) {
			studentDto = studentFileService.findAll();
		} else if (option.equals("print")) {
			studentDto = studentFileService.printStudentList();
		}

		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@GetMapping("/api/file/student/**")
	public ResponseEntity<Object> getFile(HttpServletRequest request,
			@RequestParam(value = "option", required = false) String option) {
		StudentDTO studentDto = new StudentDTO();
		if (option == null) {
			studentDto = studentFileService.findOneByFileName(request.getRequestURI().split("/api/file/student/")[1]);
		} else if (option.equals("getFile")) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + request.getRequestURI().split("/api/file/student/")[1])
					.body(studentFileService.getFile(request.getRequestURI()));
		} else if (option.equals("printResult")) {
			String studentId = request.getRequestURI().split("/api/file/student/")[1];
			studentDto = studentFileService.printResult(studentId);
		} else if (option.equals("printTimetable")) {
			String studentId = request.getRequestURI().split("/api/file/student/")[1];
			studentDto = studentFileService.printTimetable(studentId);
		}

		return new ResponseEntity<Object>(studentDto, studentDto.getHttpStatus());
	}

	@PostMapping(value = "/api/file/student")
	public ResponseEntity<StudentDTO> postFile(@RequestBody MyFileDTO fileDto) {
		StudentDTO studentDto = studentFileService.save(fileDto);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@PutMapping("/api/file/student")
	public ResponseEntity<StudentDTO> updateFile(@RequestBody MyFileDTO fileDto) {
		StudentDTO studentDto = studentFileService.update(fileDto);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

	@DeleteMapping(value = "/api/file/student")
	public ResponseEntity<StudentDTO> deleteStudent(@RequestBody MyFileDTO fileDto) {
		StudentDTO studentDto = studentFileService.delete(fileDto);
		return new ResponseEntity<StudentDTO>(studentDto, studentDto.getHttpStatus());
	}

}
