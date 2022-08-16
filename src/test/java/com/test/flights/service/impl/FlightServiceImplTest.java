package com.test.flights.service.impl;

import com.test.flights.models.FlightsData;
import com.test.flights.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceImplTest {

    @Autowired private FlightService flightService;

    FlightsData data;
    @Test
    void getFlightsToday() {
        this.data = flightService.getFlightsToday();

        assertTrue(data == null);
    }

    @Test
    void getFlightsByExistingAirlineCode() {
        String code = "EK";
        FlightsData flights = flightService.getFlightsByAirlineCode(code);

        assertTrue(flights != null);
        assertTrue(flights.getFlights().size() > 0, "No flight/s with the airline code " + code);
        assertTrue(flights.getFlights().get(0).getAirlineCode() == code, code + " is not equal to " + flights.getFlights().get(0).getAirlineCode());
    }

//    @Test
//    void saveFlight() {
//    }
//
//    @Test
//    void getAllFlights() {
//    }
}