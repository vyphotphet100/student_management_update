package com.window_programming_api.apis.file;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.file.service.IEducationTrainingFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class EducationTrainingFileAPI {

	@Autowired 
	private IEducationTrainingFileService educationTrainingFileService;
	
	@GetMapping("/api/file/education_training/**")
	public ResponseEntity<Object> getFile(HttpServletRequest request,
			@RequestParam(value = "option", required = false) String option) {
		EducationTrainingDTO educationTrainingDto = new EducationTrainingDTO();
		if (option == null) {
			educationTrainingDto = educationTrainingFileService
					.findOneByFileName(request.getRequestURI().split("/api/file/education_training/")[1]);
		} else if (option.equals("getFile")) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + request.getRequestURI().split("/api/file/education_training/")[1])
					.body(educationTrainingFileService.getFile(request.getRequestURI()));
		} 

		return new ResponseEntity<Object>(educationTrainingDto, educationTrainingDto.getHttpStatus());
	}

	@PostMapping(value = "/api/file/education_training")
	public ResponseEntity<EducationTrainingDTO> postFile(@RequestBody MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = educationTrainingFileService.save(fileDto);
		return new ResponseEntity<EducationTrainingDTO>(educationTrainingDto, educationTrainingDto.getHttpStatus());
	}

	@PutMapping("/api/file/education_training")
	public ResponseEntity<EducationTrainingDTO> updateFile(@RequestBody MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = educationTrainingFileService.update(fileDto);
		return new ResponseEntity<EducationTrainingDTO>(educationTrainingDto, educationTrainingDto.getHttpStatus());
	}

	@DeleteMapping(value = "/api/file/education_training")
	public ResponseEntity<EducationTrainingDTO> deleteStudent(@RequestBody MyFileDTO fileDto) {
		EducationTrainingDTO educationTrainingDto = educationTrainingFileService.delete(fileDto);
		return new ResponseEntity<EducationTrainingDTO>(educationTrainingDto, educationTrainingDto.getHttpStatus());
	}
}
