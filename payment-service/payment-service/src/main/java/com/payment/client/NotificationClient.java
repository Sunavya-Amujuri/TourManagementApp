package com.payment.client;

import com.payment.config.FeignClientConfig;
import com.payment.dto.NotificationRequest;
import com.payment.dto.NotificationResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification-service",
        configuration = FeignClientConfig.class
)
public interface NotificationClient {

    @PostMapping("/api/notifications/send")
    NotificationResponse sendNotification(
            @RequestBody NotificationRequest request);
}
