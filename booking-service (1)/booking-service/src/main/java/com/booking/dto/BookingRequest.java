package com.booking.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long tourId;

    private String userEmail;

    @NotNull
    @FutureOrPresent
    private LocalDate bookingDate;

    @NotNull
    @Min(1)
    private Integer numberOfPersons;
}
