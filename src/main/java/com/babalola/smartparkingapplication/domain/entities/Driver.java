package com.babalola.smartparkingapplication.domain.entities;
import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "drivers")
public class Driver extends BaseUser {

    @ManyToMany(mappedBy = "drivers")
    private List<Vehicle> vehicles;

    public Driver() {
        this.setUserType(UserTypeEnum.DRIVER);
    }



}