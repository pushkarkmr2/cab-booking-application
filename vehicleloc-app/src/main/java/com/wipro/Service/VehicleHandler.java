package com.wipro.Service;

import com.wipro.Repository.VehicleRepo;
import com.wipro.entity.VehicleLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleHandler {

    @Autowired
    VehicleRepo vehicleRepo;

    public List<VehicleLocation> findAll() {
        return vehicleRepo.findAll();
    }

    public VehicleLocation findById(int id) {
        return vehicleRepo.findById(id).orElse(new VehicleLocation());
    }

    public VehicleLocation save(VehicleLocation vehicleLocation) {
        VehicleLocation newVehicleLocation = vehicleRepo.save(vehicleLocation);
        System.out.println("New Vehicle Location received : " + newVehicleLocation);
        return newVehicleLocation;
    }
}
