package com.tour.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransportResponse {
    private Long transportId;

    private String transportType;

    private String providerName;

    private String source;

    private String destination;

    private Double price;

    private Integer availableSeats;

    private String description;
}
