package com.test.flights.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "airline_code")
    private String airlineCode;

    @Column(name = "departure_port")
    private String departurePort;

    @Column(name = "arrival_port")
    private String arrivalPort;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;
}
