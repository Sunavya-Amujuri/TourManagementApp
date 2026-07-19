package com.tour.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LodgingResponse {
    private Long lodgingId;

    private String hotelName;

    private String address;

    private String city;

    private Double pricePerNight;

    private Double rating;

    private String description;

    private Integer availableRooms;
}
