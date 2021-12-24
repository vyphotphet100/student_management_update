package com.window_programming_api.filter;

import com.window_programming_api.dto.EducationTrainingDTO;
import com.window_programming_api.dto.LecturerDTO;
import com.window_programming_api.dto.StudentDTO;
import com.window_programming_api.service.IEducationTrainingService;
import com.window_programming_api.service.ILecturerService;
import com.window_programming_api.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtRequestFilters extends OncePerRequestFilter {

	@Autowired
	private IEducationTrainingService educationTrainingService;

	@Autowired
	private IStudentService studentService;

	@Autowired
	private ILecturerService lecturerService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		UsernamePasswordAuthenticationToken authentication = null;

		if (request.getHeader("Authorization") != null) {
			String authorizationHeader = request.getHeader("Authorization");
			if (!authorizationHeader.equals("") && authorizationHeader.startsWith("Token ")) {
				String token = authorizationHeader.substring(6);

				// Check education training
				EducationTrainingDTO educationTrainingDto = educationTrainingService.findOneByTokenCode(token);
				StudentDTO studentDto = studentService.findOneByTokenCode(token);
				LecturerDTO lecturerDto = lecturerService.findOneByTokenCode(token);

				if (educationTrainingDto.getHttpStatus() == HttpStatus.OK) {
					authentication = new UsernamePasswordAuthenticationToken(educationTrainingDto, null,
							educationTrainingDto.getAuthorities());
				}
				else if (studentDto.getHttpStatus() == HttpStatus.OK) {
					authentication = new UsernamePasswordAuthenticationToken(studentDto, null,
							studentDto.getAuthorities());
				}
				else if (lecturerDto.getHttpStatus() == HttpStatus.OK) {
					authentication = new UsernamePasswordAuthenticationToken(lecturerDto, null,
							lecturerDto.getAuthorities());
				}
			}
		}

		if (authentication != null) {
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

//        if (userDto != null) {
//        	MyUser myUser = converter.toMyUser(userDto);
//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(myUser, null, myUser.getAuthorities());
//            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }

		filterChain.doFilter(request, response);

	}

}
