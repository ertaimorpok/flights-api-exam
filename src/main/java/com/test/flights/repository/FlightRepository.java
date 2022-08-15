package com.test.flights.repository;

import com.test.flights.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT f FROM Flight f WHERE f.airlineCode = :airlineCode")
    public List<Flight> getFlightsByAirlineCode(@Param("airlineCode") String airlineCode);

    @Query("SELECT f FROM Flight f WHERE f.departureTime between :before and :after")
    public List<Flight> getFlightsToday(@Param("before") LocalDateTime before, @Param("after") LocalDateTime after);
}
