package com.wipro.controller;

import com.wipro.Service.CabBookingHandler;
import com.wipro.dto.UserLocation;
import com.wipro.entity.VehicleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CabBookingController {

    @Autowired
    CabBookingHandler cabBookingHandler;

    @PostMapping("/book")
    public String bookCab(@RequestBody UserLocation userLocation) {

        Double latitude = userLocation.getLatitude();
        Double longitude = userLocation.getLongitude();

        List<VehicleLocation> nearbyCabs = cabBookingHandler.findByLatitudeBetweenAndLongitudeBetween
                (latitude - 0.01, latitude + 0.01,
                longitude - 0.01, longitude + 0.01);

        if (nearbyCabs.isEmpty()) {
            return "Sorry! No vehicles found near your location.";
        }else {
            VehicleLocation nearestVehicle = nearbyCabs.get(0);
            return "Vehicle " + nearestVehicle.getVehicleNumber() + " is assigned to user " + userLocation.getUserName();
        }
    }
}
