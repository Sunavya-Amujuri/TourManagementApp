package com.booking.client;

import com.booking.dto.NotificationRequest;
import com.booking.dto.NotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service")
public interface NotificationClient {
    @PostMapping("/notifications/send")
    NotificationResponse sendNotification(
            @RequestBody NotificationRequest request);
}
