package com.tour.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourRequest {
    @NotBlank(message = "Tour name is required")
    private String tourName;

    @Size(max = 1000)
    private String tourDescription;

    @NotBlank(message = "Tour guide is required")
    private String tourGuide;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    private List<String> meals;

    private List<String> activities;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Available seats are required")
    @Positive(message = "Available seats must be greater than zero")
    private Integer availableSeats;

    private List<String> tourImages;

    private Long locationId;

    private Long lodgingId;

    private Long transportId;

}
