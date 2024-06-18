package com.babalola.smartparkingapplication.domain.model;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "drivers")
public class Driver extends BaseUser {

    public Driver() {
        this.setUserType(UserTypeEnum.DRIVER);
    }


}