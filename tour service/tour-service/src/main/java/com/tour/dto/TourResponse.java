package com.tour.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourResponse {

    private Long id;

    private String tourName;

    private String tourDescription;

    private String tourGuide;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> meals;

    private List<String> activities;

    private Double price;

    private Integer availableSeats;

    private List<String> tourImages;

    private Long locationId;

    private Long lodgingId;

    private Long transportId;

    private LocationResponse location;

    private LodgingResponse lodging;

    private TransportResponse transport;
}
