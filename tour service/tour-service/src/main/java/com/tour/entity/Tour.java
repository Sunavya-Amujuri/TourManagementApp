package com.tour.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tours")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tourName;

    @Column(length = 1000)
    private String tourDescription;

    @Column(nullable = false)
    private String tourGuide;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @ElementCollection
    @CollectionTable(
            name = "tour_meals",
            joinColumns = @JoinColumn(name = "tour_id")
    )
    @Column(name = "meal")
    @Builder.Default
    private List<String> meals = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "tour_activities",
            joinColumns = @JoinColumn(name = "tour_id")
    )
    @Column(name = "activity")
    @Builder.Default
    private List<String> activities = new ArrayList<>();

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer availableSeats;

    @ElementCollection
    @CollectionTable(
            name = "tour_images",
            joinColumns = @JoinColumn(name = "tour_id")
    )
    @Column(name = "image_url")
    @Builder.Default
    private List<String> tourImages = new ArrayList<>();

    private Long locationId;

    private Long lodgingId;

    private Long transportId;
}
