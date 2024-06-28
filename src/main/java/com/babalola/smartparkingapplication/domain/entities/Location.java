package com.babalola.smartparkingapplication.domain.entities;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "locations")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Location extends BaseEntity {

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(nullable = true)
    private String description;
}