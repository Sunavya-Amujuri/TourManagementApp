package com.transport.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "transports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transportId;

    private String transportType;

    private String providerName;

    private String source;

    private String destination;

    private Double price;

    private Integer availableSeats;

    @Column(length = 1000)
    private String description;
}
