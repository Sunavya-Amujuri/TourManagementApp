package com.location.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResponse {
    private Long locationId;

    private String city;

    private String state;

    private String country;

    private String description;
}
