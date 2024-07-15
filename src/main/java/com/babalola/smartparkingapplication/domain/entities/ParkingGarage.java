package com.babalola.smartparkingapplication.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "parking_garages")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ParkingGarage extends BaseEntity {

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id", nullable = false)
    private ParkAddress address;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "parkingGarage", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "parkingGarage", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<AvailableParkingSpace> availableParkingSpaces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "park_owner_id")
    private ParkOwner parkOwner;
}
