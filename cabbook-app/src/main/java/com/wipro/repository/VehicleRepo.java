package com.wipro.repository;

import com.wipro.entity.VehicleLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepo extends JpaRepository<VehicleLocation,Integer> {

    VehicleLocation findByVehicleNumber(String vehicleName);

    @Query("SELECT v FROM VehicleLocation v WHERE " +
            "ROUND(v.latitude, 2) BETWEEN :latMin AND :latMax AND " +
            "ROUND(v.longitude, 2) BETWEEN :lonMin AND :lonMax")
    List<VehicleLocation> findByLatitudeBetweenAndLongitudeBetween(@Param("latMin")Double latMin, @Param("latMax")Double latMax,
                                                                   @Param("lonMin")Double lonMin, @Param("lonMax")Double lonMax);
}
