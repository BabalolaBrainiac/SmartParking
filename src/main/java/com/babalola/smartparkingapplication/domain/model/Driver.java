package com.babalola.smartparkingapplication.domain.model;
import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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