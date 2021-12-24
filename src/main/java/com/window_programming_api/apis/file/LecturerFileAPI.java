package com.window_programming_api.apis.file;

import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.MyFileDTO;
import com.window_programming_api.file.service.ILecturerFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LecturerFileAPI {

	@Autowired 
	private ILecturerFileService lecturerFileService;
	
	@GetMapping("/api/file/lecturer/**")
	public ResponseEntity<Object> getFile(HttpServletRequest request,
			@RequestParam(value = "option", required = false) String option) {
		LecturerDTO lecturerDto = new LecturerDTO();
		if (option == null) {
			lecturerDto = lecturerFileService
					.findOneByFileName(request.getRequestURI().split("/api/file/lecturer/")[1]);
		} else if (option.equals("getFile")) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + request.getRequestURI().split("/api/file/lecturer/")[1])
					.body(lecturerFileService.getFile(request.getRequestURI()));
		} 

		return new ResponseEntity<Object>(lecturerDto, lecturerDto.getHttpStatus());
	}

	@PostMapping(value = "/api/file/lecturer")
	public ResponseEntity<LecturerDTO> postFile(@RequestBody MyFileDTO fileDto) {
		LecturerDTO lecturerDto = lecturerFileService.save(fileDto);
		return new ResponseEntity<LecturerDTO>(lecturerDto, lecturerDto.getHttpStatus());
	}

	@PutMapping("/api/file/lecturer")
	public ResponseEntity<LecturerDTO> updateFile(@RequestBody MyFileDTO fileDto) {
		LecturerDTO lecturerDto = lecturerFileService.update(fileDto);
		return new ResponseEntity<LecturerDTO>(lecturerDto, lecturerDto.getHttpStatus());
	}

	@DeleteMapping(value = "/api/file/lecturer")
	public ResponseEntity<LecturerDTO> deleteStudent(@RequestBody MyFileDTO fileDto) {
		LecturerDTO lecturerDto = lecturerFileService.delete(fileDto);
		return new ResponseEntity<LecturerDTO>(lecturerDto, lecturerDto.getHttpStatus());
	}
}
