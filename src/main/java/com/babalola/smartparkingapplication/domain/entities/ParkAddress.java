package com.babalola.smartparkingapplication.domain.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "park_address")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ParkAddress extends BaseEntity {

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
}