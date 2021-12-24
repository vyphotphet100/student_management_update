package com.window_programming_api.apis.file;

import com.window_programming_api.dto.RegisterDTO;
import com.window_programming_api.file.service.IRegisterFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RegisterFileAPI {

	@Autowired
	private IRegisterFileService registerFileService;

	@GetMapping("/api/file/register")
	public ResponseEntity<RegisterDTO> getRegisterFile(
			@RequestParam(value = "option", required = false) String option) {
		RegisterDTO registerDto = new RegisterDTO();
		if (option.equals("print")) {
			registerDto = registerFileService.printRegisterList();
		}

		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@GetMapping("/api/file/register/section_class_id/{sectionClassId}")
	public ResponseEntity<RegisterDTO> getRegisterFileBySectionClassId(
			@RequestParam(value = "option", required = false) String option,
			@PathVariable("sectionClassId") String sectionClassId) {
		RegisterDTO registerDto = new RegisterDTO();
		if (option.equals("print")) {
			registerDto = registerFileService.printRegisterBySectionClass2(sectionClassId);
		}

		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@GetMapping("/api/file/register/**")
	public ResponseEntity<Object> getFile(@RequestParam(value = "option", required = false) String option,
			HttpServletRequest request) {
		if (option.equals("getFile")) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + request.getRequestURI().split("/api/file/register/")[1])
					.body(registerFileService.getFile(request.getRequestURI()));
		}

		return null;
	}
}
