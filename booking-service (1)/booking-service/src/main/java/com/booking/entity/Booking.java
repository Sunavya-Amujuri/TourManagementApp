package com.booking.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    private Long userId;

    private Long tourId;

    private LocalDate bookingDate;

    private Integer numberOfPersons;

    private Double totalAmount;

    private String bookingStatus;

    private String userEmail;
}
