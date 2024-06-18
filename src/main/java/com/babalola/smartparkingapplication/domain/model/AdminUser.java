package com.babalola.smartparkingapplication.domain.model;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin_user")
@Data
public class AdminUser extends BaseUser {

    public AdminUser() {
        this.setUserType(UserTypeEnum.ADMIN);
    }

}