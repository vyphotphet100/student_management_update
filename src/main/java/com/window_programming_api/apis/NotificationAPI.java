package com.window_programming_api.apis;

import com.window_programming_api.dto.NotificationDTO;
import com.window_programming_api.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationAPI {

	@Autowired
	private INotificationService notificationService;
	
	@GetMapping("/api/notification")
	public ResponseEntity<NotificationDTO> getNotification() {
		NotificationDTO notificationDto = notificationService.findAll();
		return new ResponseEntity<NotificationDTO>(notificationDto, notificationDto.getHttpStatus());
	}
	
	@GetMapping("/api/notification/{id}")
	public ResponseEntity<NotificationDTO> getOneNotification(@PathVariable("id") Long id) {
		NotificationDTO notificationDto = notificationService.findOne(id);
		return new ResponseEntity<NotificationDTO>(notificationDto, notificationDto.getHttpStatus());
	}

	@PostMapping("/api/notification")
	public ResponseEntity<NotificationDTO> postNotification(@RequestBody NotificationDTO notificationDto) {
		notificationDto = notificationService.save(notificationDto);
		return new ResponseEntity<NotificationDTO>(notificationDto, notificationDto.getHttpStatus());
	}

	@PutMapping("/api/notification")
	public ResponseEntity<NotificationDTO> putNotification(@RequestBody NotificationDTO notificationDto) {
		notificationDto = notificationService.update(notificationDto);
		return new ResponseEntity<NotificationDTO>(notificationDto, notificationDto.getHttpStatus());
	}
	
	@PreAuthorize("hasAnyAuthority('ADMIN')")
	@DeleteMapping("/api/notification/{id}")
	public ResponseEntity<NotificationDTO> deleteNotification(@PathVariable("id") Long id) {
		NotificationDTO notificationDto = notificationService.delete(id);
		return new ResponseEntity<NotificationDTO>(notificationDto, notificationDto.getHttpStatus());
	}
}
