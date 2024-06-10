package com.wipro.Repository;

import com.wipro.entity.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleLocation,Integer> {
}
