package com.window_programming_api.service.impl;

import com.window_programming_api.dto.NotificationDTO;
import com.window_programming_api.entity.NotificationEntity;
import com.window_programming_api.repository.NotificationRepository;
import com.window_programming_api.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService extends BaseService implements INotificationService {

	@Autowired
	private NotificationRepository notificationRepo;

	@Override
	public NotificationDTO findAll() {
		NotificationDTO notificationDto = new NotificationDTO();
		List<NotificationEntity> notificationEntities = notificationRepo.findAll();

		if (!notificationEntities.isEmpty()) {
			for (NotificationEntity notificationEntity : notificationEntities)
				notificationDto.getListResult().add(this.converter.toDTO(notificationEntity, NotificationDTO.class));

			notificationDto.setMessage("Load notification list successfully.");
			return notificationDto;
		}

		return (NotificationDTO) this.ExceptionObject(notificationDto, "There is no notification.");
	}

	@Override
	public NotificationDTO findOne(Long id) {
		NotificationDTO notificationDto = new NotificationDTO();
		NotificationEntity notificationEntity = notificationRepo.findById(id).orElse(null);

		if (notificationEntity != null) {
			notificationDto = this.converter.toDTO(notificationEntity, NotificationDTO.class);
			notificationDto.setMessage("Get notification having id = " + id + " successfully.");
			return notificationDto;
		}

		return (NotificationDTO) this.ExceptionObject(notificationDto, "Notification does not exist.");
	}

	@Override
	public NotificationDTO save(NotificationDTO notificationDto) {
		try {
			notificationRepo.save(this.converter.toEntity(notificationDto, NotificationEntity.class));
			notificationDto.setMessage("Add notification successfully.");
			return notificationDto;
		} catch(Exception ex) {
			return (NotificationDTO) this.ExceptionObject(notificationDto, "Something's wrong.");
		}
		
	}

	@Override
	public NotificationDTO update(NotificationDTO notificationDto) {

		if (notificationRepo.findById(notificationDto.getId()) != null) {
			NotificationEntity notificationEntity = notificationRepo.save(this.converter.toEntity(notificationDto, NotificationEntity.class));
			notificationDto = this.converter.toDTO(notificationEntity, NotificationDTO.class);
			notificationDto.setMessage("Update notification having id = " + notificationDto.getId() + " successfully.");
			return notificationDto;
		}

		return (NotificationDTO) this.ExceptionObject(notificationDto, "Notification does not exist.");
	}

	@Override
	public NotificationDTO delete(Long id) {
		NotificationDTO notificationDto = new NotificationDTO();
		if (notificationRepo.findById(id) != null) {
			notificationRepo.deleteById(id);
			notificationDto.setMessage("Delete notification having id = " + id + " successfully.");
			return notificationDto;
		}

		return (NotificationDTO) this.ExceptionObject(notificationDto, "Notification does not exist.");
	}

}
