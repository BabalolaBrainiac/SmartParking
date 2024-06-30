package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "admin_users", indexes = {
        @Index(name = "idx_first_name", columnList = "firstName"),
        @Index(name = "idx_phone_number", columnList = "phoneNumber")
})
@Data
public class AdminUser extends BaseUser {

    public AdminUser() {
        this.setUserType(UserTypeEnum.ADMIN);
    }

}