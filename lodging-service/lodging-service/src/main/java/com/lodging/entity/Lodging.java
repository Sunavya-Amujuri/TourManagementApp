package com.lodging.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lodgings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lodging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lodgingId;

    @Column(nullable = false)
    private String hotelName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Double pricePerNight;

    @Column(nullable = false)
    private Double rating;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private Integer availableRooms;
}
