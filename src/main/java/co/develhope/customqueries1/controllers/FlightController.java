package co.develhope.customqueries1.controllers;

import co.develhope.customqueries1.entities.Flight;
import co.develhope.customqueries1.entities.FlightStatusEnum;
import co.develhope.customqueries1.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("flights")
public class FlightController{

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("provision")
    public ResponseEntity<List<Flight>> provisionFlight(){
        List<Flight> flights = new ArrayList<>();
        Random random = new Random();
        for(int i = 0 ; i < 50 ; i++){
            Flight flight = new Flight();
            flight.setDescription("Description: "+random.ints(100,9999).findFirst().getAsInt());
            flight.setFromAirport("From airport: "+random.ints(100,9999).findFirst().getAsInt());
            flight.setToAirport("To airport: "+random.ints(100,9999).findFirst().getAsInt());
            flight.setFlightStatus(FlightStatusEnum.ON_TIME);
            flights.add(flight);
        }
        flightRepository.saveAllAndFlush(flights);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("")
    public List<Flight> getFlightList(){
        return flightRepository.findAll();
    }
}




