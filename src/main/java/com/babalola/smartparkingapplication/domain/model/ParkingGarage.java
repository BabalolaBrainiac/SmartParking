package com.babalola.smartparkingapplication.domain.model;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.List;

@Entity
@Table(name = "parking_garages")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ParkingGarage extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private ParkAddress address;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany(mappedBy = "parkingGarage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

    @OneToMany(mappedBy = "parkingGarage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailableParkingSpace> availableParkingSpaces;
}
