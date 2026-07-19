package com.lodging.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LodgingRequest {
    @NotBlank
    private String hotelName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotNull
    @Positive
    private Double pricePerNight;

    @NotNull
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private Double rating;

    private String description;

    @NotNull
    @PositiveOrZero
    private Integer availableRooms;
}
