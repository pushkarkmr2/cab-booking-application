package com.wipro.Service;

import com.wipro.repository.VehicleRepo;
import com.wipro.entity.VehicleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CabBookingHandler {

    @Autowired
    VehicleRepo vehicleRepo;

    @KafkaListener(topics = "vehicle_location", groupId = "group-1")
    public void vehicleLocationListener(VehicleLocation vehicleLocation) {
        System.out.println("came to cab booking handler : " + vehicleLocation.toString());
        VehicleLocation existingVehicleLocation = vehicleRepo.findByVehicleNumber(vehicleLocation.getVehicleNumber());
        if (existingVehicleLocation != null) {
            existingVehicleLocation.setLatitude(vehicleLocation.getLatitude());
            existingVehicleLocation.setLongitude(vehicleLocation.getLongitude());
            vehicleRepo.save(existingVehicleLocation);
        } else {
            vehicleRepo.save(vehicleLocation);
        }
    }

    public List<VehicleLocation> findByLatitudeBetweenAndLongitudeBetween(Double latMin, Double latMax, Double lonMin, Double lonMax){
        return  vehicleRepo.findByLatitudeBetweenAndLongitudeBetween(latMin, latMax, lonMin, lonMax);
    }


}
