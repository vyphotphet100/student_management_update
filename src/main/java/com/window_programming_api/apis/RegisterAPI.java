package com.window_programming_api.apis;

import com.window_programming_api.dto.RegisterDTO;
import com.window_programming_api.service.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterAPI {
	
	@Autowired
	private IRegisterService registerService;

	@GetMapping("/api/register")
	public ResponseEntity<RegisterDTO> getRegister() {
		RegisterDTO registerDto = registerService.findAll();
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@GetMapping("/api/register/{id}")
	public ResponseEntity<RegisterDTO> getOneRegister(@PathVariable("id") Long id) {
		RegisterDTO registerDto = registerService.findOne(id);
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@PostMapping("/api/register")
	public ResponseEntity<RegisterDTO> postRegister(@RequestBody RegisterDTO registerDto) {
		registerDto = registerService.save(registerDto);
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@PutMapping("/api/register")
	public ResponseEntity<RegisterDTO> putRegister(@RequestBody RegisterDTO registerDto) {
		registerDto = registerService.update(registerDto);
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@DeleteMapping("/api/register/{id}")
	public ResponseEntity<RegisterDTO> deleteRegister(@PathVariable("id") Long id) {
		RegisterDTO registerDto = registerService.delete(id);
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}

	@GetMapping("/api/register/student_id/{studentId}")
	public ResponseEntity<RegisterDTO> getAllByStudentId(@PathVariable("studentId") String studentId) {
		RegisterDTO registerDto = registerService.findAllByStudentId(studentId);
		return new ResponseEntity<RegisterDTO>(registerDto, registerDto.getHttpStatus());
	}
}
