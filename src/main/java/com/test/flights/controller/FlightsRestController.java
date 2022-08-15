package com.test.flights.controller;

import com.test.flights.models.ApiError;
import com.test.flights.models.Flight;
import com.test.flights.models.FlightsData;
import com.test.flights.service.FlightService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsRestController {

    @Autowired
    private FlightService flightService;

    @RequestMapping(value = "/currentflights", method = RequestMethod.GET)
    public ResponseEntity getCurrentFlights() {

        try {
            FlightsData data = flightService.getFlightsToday();

            if (null == data) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, "No Current Flights Found"));
            } else {
                return ResponseEntity.ok(data);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage()));
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAllFlights() {

        try {
            FlightsData data = flightService.getAllFlights();

            if (null == data) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, "No Current Flights Found"));
            } else {
                return ResponseEntity.ok(data);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage()));
        }
    }

    @RequestMapping(value = "/{ac}", method = RequestMethod.GET)
    public ResponseEntity getFlightsByAirlineCode(@PathVariable("ac") String airlineCode) {
        if (StringUtils.isBlank(airlineCode)) {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_GATEWAY, "Airline Code is blank"));
        }

        try {
            FlightsData data = flightService.getFlightsByAirlineCode(airlineCode);

            if (null == data) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND, "No Flights Found for " + airlineCode));
            } else {
                return ResponseEntity.ok(data);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage()));
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity addFlight(@RequestBody Flight flight) {
        List<String> errors = new ArrayList<String>();

        if (StringUtils.isBlank(flight.getFlightNumber()))
            errors.add("Flight Number is blank");

        if (StringUtils.isBlank(flight.getAirlineCode()))
            errors.add("Airline Code Number is blank");

        if (StringUtils.isBlank(flight.getArrivalPort()))
            errors.add("Arrival Port is blank");

        if (StringUtils.isBlank(flight.getDeparturePort()))
            errors.add("Departure Port is blank");

        if (flight.getArrivalTime() == null)
            errors.add("Arrival Time is blank");

        if (flight.getDepartureTime() == null)
            errors.add("Departure Time is blank");

        if (errors.size() == 0) {
            try {
                flightService.saveFlight(flight);
                return ResponseEntity.status(HttpStatus.CREATED).body("Flight " + flight.getFlightNumber() + " saved");
            } catch (Exception e) {
                return ResponseEntity.internalServerError().body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage()));
            }
        } else {
            return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_GATEWAY, errors));
        }
    }
}
