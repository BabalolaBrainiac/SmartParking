package com.babalola.smartparkingapplication.domain.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehicle")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Vehicle extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = true)
    private Integer year;

    @Column(nullable = true)
    private String color;

    @Column(nullable = false)
    private String vin;

    @Column(nullable = true)
    private String licensePlate;

    @Column(nullable = false)
    private String vehicleType; // e.g., Sedan, SUV, Truck, etc.

    @Column(nullable = true)
    private Integer mileage;

    @Column(nullable = true)
    private String fuelType; // e.g., Gasoline, Diesel, Electric, Hybrid

    @Column(nullable = true)
    private Boolean isElectric;

    @ManyToOne
    @JoinColumn(name = "parking_garage_id", nullable = false)
    private ParkingGarage parkingGarage;

    @ManyToMany
    @JoinTable(
            name = "vehicle_drivers",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> drivers;

}
