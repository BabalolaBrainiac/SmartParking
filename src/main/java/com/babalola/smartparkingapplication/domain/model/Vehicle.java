package com.babalola.smartparkingapplication.domain.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Vehicle extends BaseEntity {
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
    private String vehicleType;

    @Column(nullable = true)
    private Integer mileage;

    @Column(nullable = true)
    private String fuelType;

    @Column(nullable = true)
    private Boolean isElectric;

}
