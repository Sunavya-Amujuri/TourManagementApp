package com.notification.service;

import com.notification.dto.NotificationRequest;
import com.notification.dto.NotificationResponse;
import com.notification.entity.Notification;
import com.notification.notification.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repository;
    private final EmailService emailService;

    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {

        emailService.sendEmail(
                request.getRecipient(),
                request.getSubject(),
                request.getMessage()
        );

        Notification notification = new Notification();

        notification.setBookingId(request.getBookingId());
        notification.setRecipient(request.getRecipient());
        notification.setSubject(request.getSubject());
        notification.setMessage(request.getMessage());
        notification.setStatus("SENT");
        notification.setSentAt(LocalDateTime.now());

        Notification saved = repository.save(notification);

        return new NotificationResponse(
                saved.getId(),
                saved.getRecipient(),
                saved.getStatus(),
                saved.getSentAt()
        );
    }
}
