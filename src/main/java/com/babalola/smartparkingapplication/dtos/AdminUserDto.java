package com.babalola.smartparkingapplication.dtos;

public record AdminUserDto( Long id,
                            String firstName,
                            String lastName,
                            String email,
                            String phoneNumber,
                            Boolean isDeleted,
                            String userType) {
}
