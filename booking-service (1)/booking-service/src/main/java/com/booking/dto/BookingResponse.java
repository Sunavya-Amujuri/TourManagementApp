package com.booking.dto;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Long bookingId;

    private Long userId;

    private Long tourId;

    private String userEmail;

    private LocalDate bookingDate;

    private Integer numberOfPersons;

    private Double totalAmount;

    private String bookingStatus;
}
