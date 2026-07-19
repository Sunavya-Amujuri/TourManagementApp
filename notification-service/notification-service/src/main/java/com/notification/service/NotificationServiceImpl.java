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

    @Override
    public NotificationResponse sendNotification(NotificationRequest request) {

        System.out.println("================================");
        System.out.println("Sending Notification");
        System.out.println("To : " + request.getRecipient());
        System.out.println("Subject : " + request.getSubject());
        System.out.println("Message : " + request.getMessage());
        System.out.println("================================");

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
