package com.berksefkatli.garage.controller;

import com.berksefkatli.garage.model.Vehicle;
import com.berksefkatli.garage.service.GarageService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GarageController {

    private final GarageService garageService;

    public GarageController(GarageService garageService) {
        this.garageService = garageService;
    }

    @PostMapping(path = "/park", produces = "application/json")
    @ResponseBody
    public String park(@RequestBody Vehicle vehicle) {
        return garageService.park(vehicle);
    }

    @DeleteMapping(path = "/leave", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    public void leave(@RequestParam("ticketId") int ticketId) {
        garageService.leave(ticketId);
    }

    @GetMapping(path = "/status", produces = "application/json")
    @ResponseBody
    public String status() {
        return garageService.status();
    }
}
