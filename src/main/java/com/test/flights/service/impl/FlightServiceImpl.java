package com.test.flights.service.impl;

import com.test.flights.models.Flight;
import com.test.flights.models.FlightsData;
import com.test.flights.repository.FlightRepository;
import com.test.flights.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository repository;

    @Override
    public FlightsData getFlightsToday() {

        LocalDateTime before = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime after = before.withHour(23).withMinute(59).withSecond(59);

        List<Flight> flights = repository.getFlightsToday(before, after);

        if (flights.size() == 0) {
            return null;
        } else {
            FlightsData data = new FlightsData();
            data.setFlights(flights);
            return data;
        }
    }

    @Override
    public FlightsData getFlightsByAirlineCode(String airlineCode) {
        List<Flight> flights = repository.getFlightsByAirlineCode(airlineCode);

        if (flights.size() == 0) {
            return null;
        } else {
            FlightsData data = new FlightsData();
            data.setFlights(flights);
            return data;
        }
    }

    @Override
    public void saveFlight(Flight flight) {
        repository.save(flight);
    }

    @Override
    public FlightsData getAllFlights() {
        List<Flight> flights = repository.findAll();

        if (flights.size() == 0) {
            return null;
        } else {
            FlightsData data = new FlightsData();
            data.setFlights(flights);
            return data;
        }
    }
}
