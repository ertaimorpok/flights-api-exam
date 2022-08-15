package com.test.flights.service;

import com.test.flights.models.Flight;
import com.test.flights.models.FlightsData;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface FlightService {

    public FlightsData getFlightsToday();

    public FlightsData getFlightsByAirlineCode(String airlineCode);

    public void saveFlight(Flight flight);

    public FlightsData getAllFlights();
}
