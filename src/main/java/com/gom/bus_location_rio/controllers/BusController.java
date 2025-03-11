package com.gom.bus_location_rio.controllers;

import com.gom.bus_location_rio.dto.Bus;
import com.gom.bus_location_rio.services.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public List<Bus> getAllBus() {
        return busService.getAllBus();
    }

    @GetMapping("/selectBus")
    public ResponseEntity<List<Bus>> getBusesByLine(@RequestParam String line) {
        List<Bus> filteredBuses = busService.getBusByLine(line);

        if (filteredBuses.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(filteredBuses);
        }
    }
}
