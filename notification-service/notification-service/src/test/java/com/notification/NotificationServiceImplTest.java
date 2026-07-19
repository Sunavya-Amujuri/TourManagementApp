package com.notification;

import com.notification.dto.NotificationRequest;
import com.notification.dto.NotificationResponse;
import com.notification.notification.NotificationRepository;
import com.notification.service.NotificationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.notification.entity.Notification;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class NotificationServiceImplTest {
    @Mock
    // Mockito creates a fake object. Instead of calling PostgreSQL, it creates something like: NotificationRepository repository = FakeRepository();
    private NotificationRepository repository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @Test
    void contextLoads() {
        NotificationRequest request = new NotificationRequest();

        request.setBookingId(1L);
        request.setRecipient("user@gmail.com");
        request.setSubject("Booking Confirmed");
        request.setMessage("Your booking has been confirmed.");

        Notification savedNotification = new Notification();

        savedNotification.setId(1L);
        savedNotification.setBookingId(1L);
        savedNotification.setRecipient("user@gmail.com");
        savedNotification.setSubject("Booking Confirmed");
        savedNotification.setMessage("Your booking has been confirmed.");
        savedNotification.setStatus("SENT");
        savedNotification.setSentAt(LocalDateTime.now());

        when(repository.save(any(Notification.class)))
                .thenReturn(savedNotification);

        // call the service
        NotificationResponse response = notificationService.sendNotification(request);

        // Assertions
        assertNotNull(response);
        assertEquals("user@gmail.com", response.getRecipient());
        assertEquals("SENT", response.getStatus());
        assertNotNull(response.getSentAt());

        // Verify interaction
        verify(repository, times(1)).save(any(Notification.class));
    }
}

// JUnit = "Run my test and check the results."
//Mockito = "Don't use the real database or external services; use fake objects instead."

// As a tester, you want to know:
//
//"Did my service actually call the repository?"
