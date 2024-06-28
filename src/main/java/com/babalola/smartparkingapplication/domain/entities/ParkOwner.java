package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "park_owners", indexes = {
        @Index(name = "idx_first_name", columnList = "firstName"),
        @Index(name = "idx_phone_number", columnList = "phoneNumber")
})
@Getter
@Setter
public class ParkOwner extends BaseUser {

    @OneToMany(mappedBy = "parkOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ParkingGarage> parkingGarages;

    public ParkOwner() {
        this.setUserType(UserTypeEnum.PARK_OWNER);
    }
}
