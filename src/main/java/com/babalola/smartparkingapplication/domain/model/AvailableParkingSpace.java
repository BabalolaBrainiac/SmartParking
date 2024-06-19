package com.babalola.smartparkingapplication.domain.model;
import com.babalola.smartparkingapplication.domain.enums.VehicleTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "available_parking_spaces")
@Data
@NoArgsConstructor
public class AvailableParkingSpace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleTypeEnum vehicleType;

    @Column(nullable = false)
    private Integer availableSpaces;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_garage_id", nullable = false)
    private ParkingGarage parkingGarage;
}