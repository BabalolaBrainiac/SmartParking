package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "park_owners", indexes = {
        @Index(name = "idx_first_name", columnList = "first_name"),
        @Index(name = "idx_phone_number", columnList = "phone_number")
})
@Getter
@Setter
public class ParkOwner extends BaseUser {

    @OneToMany(mappedBy = "parkOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ParkingGarage> parkingGarages;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ParkOwner() {
        this.setUserType(UserTypeEnum.PARK_OWNER);
    }
}