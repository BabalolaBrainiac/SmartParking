package com.babalola.smartparkingapplication.domain.entities;

import com.babalola.smartparkingapplication.auth.roles.UserRoles;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user_data")
public class UserAuthDetails implements UserDetails {

    @Column(unique = true)
    protected String username;
    @Column(nullable = false)
    protected String password;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UserRoles role;


    public UserAuthDetails(String username, String password, UserRoles role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserAuthDetails() {

    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return switch (role) {
            case ADMIN_USER -> List.of(new SimpleGrantedAuthority("ADMIN_USER"), new SimpleGrantedAuthority("ADMIN_USER"));
            case DRIVER -> List.of(new SimpleGrantedAuthority("DRIVER"), new SimpleGrantedAuthority("DRIVER"));
            case PARK_OWNER -> List.of(new SimpleGrantedAuthority("PARK_OWNER"), new SimpleGrantedAuthority("PARK_OWNER"));
            default -> List.of(new SimpleGrantedAuthority("ROLE_USER"));
        };
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
