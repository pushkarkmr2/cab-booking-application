package com.wipro.controller;

import com.wipro.Service.VehicleHandler;
import com.wipro.entity.VehicleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vehicle")
public class VehicleLocationController {

    @Autowired
    VehicleHandler vehicleHandler;

    @Autowired
    KafkaTemplate<String, VehicleLocation> kafkaTemplate;

    @PostMapping
    public VehicleLocation save(@RequestBody VehicleLocation vehicleLocation) {
        VehicleLocation newVehicleLocation = vehicleHandler.save(vehicleLocation);
        kafkaTemplate.send("vehicle_location", newVehicleLocation);
        return newVehicleLocation;
    }

    @GetMapping
    public List<VehicleLocation> findAll() {
        return vehicleHandler.findAll();
    }

    @GetMapping("/{id}")
    public VehicleLocation findById(@PathVariable int id) {
        return vehicleHandler.findById(id);
    }

}
