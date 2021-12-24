package com.window_programming_api.apis;

import com.window_programming_api.dto.SectionClassDTO;
import com.window_programming_api.service.ISectionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class SectionClassAPI {
	
	@Autowired
	private ISectionClassService sectionClassService;

	@GetMapping("/api/section_class")
	public ResponseEntity<SectionClassDTO> getSectionClass() {
		SectionClassDTO sectionClassDto = sectionClassService.findAll();
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@GetMapping("/api/section_class/{id}")
	public ResponseEntity<SectionClassDTO> getOneSectionClass(@PathVariable("id") String id) {
		SectionClassDTO sectionClassDto = sectionClassService.findOne(id);
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@PostMapping("/api/section_class")
	public ResponseEntity<SectionClassDTO> postectionClass(@RequestBody SectionClassDTO sectionClassDto) {
		sectionClassDto = sectionClassService.save(sectionClassDto);
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@PutMapping("/api/section_class")
	public ResponseEntity<SectionClassDTO> putSectionClass(@RequestBody SectionClassDTO sectionClassDto) {
		sectionClassDto = sectionClassService.update(sectionClassDto);
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@DeleteMapping("/api/section_class/{id}")
	public ResponseEntity<SectionClassDTO> deleteSectionClass(@PathVariable("id") String id) {
		SectionClassDTO sectionClassDto = sectionClassService.delete(id);
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}

	@GetMapping("/api/section_class/lecturer_id/{lecturerId}")
	public ResponseEntity<SectionClassDTO> getAllByLecturerId(@PathVariable("lecturerId") Long lecturerId) {
		SectionClassDTO sectionClassDto = sectionClassService.findAllByLecturerId(lecturerId);
		return new ResponseEntity<SectionClassDTO>(sectionClassDto, sectionClassDto.getHttpStatus());
	}
}
