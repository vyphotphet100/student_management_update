package com.window_programming_api.apis.file;

import com.window_programming_api.dto.SectionClassDTO;
import com.window_programming_api.file.service.ISectionClassFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SectionClassFileAPI {

	@Autowired
	private ISectionClassFileService sectionClassFileService;

	@GetMapping("/api/file/section_class")
	public ResponseEntity<SectionClassDTO> getSectionClassFile(
			@RequestParam(value = "option", required = false) String option) {
		SectionClassDTO sectionClassDto = new SectionClassDTO();
		if (option.equals("print")) {
			sectionClassDto = sectionClassFileService.printSectionClassList();
		}

		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@GetMapping("/api/file/section_class/**")
	public ResponseEntity<Object> getFile(@RequestParam(value = "option", required = false) String option, HttpServletRequest request) {
		if (option.equals("getFile")) {
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment;filename=" + request.getRequestURI().split("/api/file/section_class/")[1])
					.body(sectionClassFileService.getFile(request.getRequestURI()));
		}
		
		return null;
	}
}
