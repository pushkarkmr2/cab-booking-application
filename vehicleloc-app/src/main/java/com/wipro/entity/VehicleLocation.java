package com.wipro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "vehicle_location")
@ToString
public class VehicleLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String vehicleNumber;
    @Column
    private Double latitude;
    @Column
    private Double longitude;

}
