package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.domain.enums.UserTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;


@MappedSuperclass
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseUser extends BaseEntity<Long> {

    @Column(unique = true)
    protected String username;
    @Column()
    protected String password;
    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory")
    @Size(max = 50, message = "First name must be less than 50 characters")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory")
    @Size(max = 50, message = "Last name must be less than 50 characters")
    private String lastName;
    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "phone_number", nullable = false, unique = true)
    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Phone number is invalid")
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserTypeEnum userType;

    @Column(name = "uuid", unique = true, nullable = false, updatable = false)
    private UUID userId;

    public void onCreate() {
        super.onCreate();
        if (userId == null) {
            userId = UuidCreator.getTimeOrderedEpoch();
        }
    }

}