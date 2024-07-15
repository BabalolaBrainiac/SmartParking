package com.babalola.smartparkingapplication.domain.entities;
import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "drivers", indexes = {
        @Index(name = "idx_first_name", columnList = "first_name"),
        @Index(name = "idx_phone_number", columnList = "phone_number")
})
public class Driver extends BaseUser {

    @JsonIgnore
    @ManyToMany(mappedBy = "drivers")
    private List<Vehicle> vehicles;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Driver() {
        this.setUserType(UserTypeEnum.DRIVER);
    }
}