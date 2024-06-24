package com.babalola.smartparkingapplication.controllers;
import com.babalola.smartparkingapplication.dtos.AdminUserDto;
import com.babalola.smartparkingapplication.exceptions.ResourceExistsException;
import com.babalola.smartparkingapplication.models.AdminUserRequest;
import com.babalola.smartparkingapplication.services.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    private final AdminUserService adminUserService;
    @Autowired
    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @Operation(summary = "Create a new AdminUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AdminUser created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<?> createAdminUser(@RequestBody AdminUserDto adminUserDto) {
        try {
            AdminUserDto savedAdminUser = adminUserService.save(adminUserDto);
            return new ResponseEntity<>(savedAdminUser, HttpStatus.CREATED);
        } catch (ResourceExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Get an AdminUser by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AdminUser found"),
            @ApiResponse(responseCode = "404", description = "AdminUser not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AdminUserDto> getAdminUserById(@PathVariable Long id) {
        return adminUserService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all AdminUsers")
    @ApiResponse(responseCode = "200", description = "List of AdminUsers")
    @GetMapping
    public ResponseEntity<List<AdminUserDto>> getAllAdminUsers() {
        List<AdminUserDto> adminUsers = adminUserService.findAll();
        return ResponseEntity.ok(adminUsers);
    }

    @Operation(summary = "Update an AdminUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AdminUser updated successfully"),
            @ApiResponse(responseCode = "404", description = "AdminUser not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AdminUserDto> updateAdminUser(@PathVariable Long id, @RequestBody AdminUserDto adminUserDTO) {
        AdminUserDto updatedAdminUser = adminUserService.update(adminUserDTO);
        return ResponseEntity.ok(updatedAdminUser);
    }

    @Operation(summary = "Delete an AdminUser")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "AdminUser deleted successfully"),
            @ApiResponse(responseCode = "404", description = "AdminUser not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable Long id) {
        adminUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
