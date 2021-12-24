package com.window_programming_api.apis;

import com.window_programming_api.dto.AbstractDTO;
import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.service.IEducationTrainingService;
import com.window_programming_api.service.ILecturerService;
import com.window_programming_api.service.ILoginService;
import com.window_programming_api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

//@CrossOrigin
@RestController
public class LoginAPI {
	@Autowired
	private ILoginService loginService;

	@Autowired
	private IEducationTrainingService educationTrainingService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ILecturerService lecturerService;

	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody StudentDTO dto) {
		AbstractDTO obj = (AbstractDTO)loginService.login(dto.getUsername(), dto.getPassword());
		return new ResponseEntity<Object>(obj, obj.getHttpStatus());
	}

	@GetMapping("/api/current_user")
	public ResponseEntity<Object> currentUser(HttpServletRequest request) {
		if (request.getHeader("Authorization") != null) {
			String authorizationHeader = request.getHeader("Authorization");
			if (!authorizationHeader.equals("") && authorizationHeader.startsWith("Token ")) {
				String token = authorizationHeader.substring(6);

				// Check education training
				EducationTrainingDTO educationTrainingDto = educationTrainingService.findOneByTokenCode(token);
				StudentDTO studentDto = studentService.findOneByTokenCode(token);
				LecturerDTO lecturerDto = lecturerService.findOneByTokenCode(token);

				if (educationTrainingDto.getHttpStatus().equals(HttpStatus.OK))
					return new ResponseEntity<>(educationTrainingDto, educationTrainingDto.getHttpStatus());
				if (studentDto.getHttpStatus().equals(HttpStatus.OK))
					return new ResponseEntity<>(studentDto, studentDto.getHttpStatus());
				if (lecturerDto.getHttpStatus().equals(HttpStatus.OK))
					return new ResponseEntity<>(lecturerDto, lecturerDto.getHttpStatus());
			}
		}
		return null;
	}
	
}
