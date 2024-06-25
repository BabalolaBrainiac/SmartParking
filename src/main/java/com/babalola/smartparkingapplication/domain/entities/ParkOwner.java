package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "park_owners")
public class ParkOwner extends BaseUser {

    public ParkOwner() {
        this.setUserType(UserTypeEnum.PARK_OWNER);
    }

}