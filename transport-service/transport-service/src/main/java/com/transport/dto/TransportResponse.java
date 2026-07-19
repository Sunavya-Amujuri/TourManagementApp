package com.transport.dto;

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
