package com.babalola.smartparkingapplication.domain.entities;
import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import jakarta.persistence.Table;



import java.util.List;

@Entity
@Table(name = "drivers", indexes = {
        @Index(name = "idx_first_name", columnList = "firstName"),
        @Index(name = "idx_phone_number", columnList = "phoneNumber")
})
public class Driver extends BaseUser {

    @ManyToMany(mappedBy = "drivers")
    private List<Vehicle> vehicles;

    public Driver() {
        this.setUserType(UserTypeEnum.DRIVER);
    }



}