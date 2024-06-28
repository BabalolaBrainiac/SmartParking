package com.babalola.smartparkingapplication.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class BaseEntity<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Exclude
    @Column(name = "created_date", nullable = false, updatable = false)
    @PastOrPresent(message = "Created date cannot be in the future")
    private LocalDateTime createdDate;

    @EqualsAndHashCode.Exclude
    @Column(name = "modified_date")
    @PastOrPresent(message = "Modified date cannot be in the future")
    private LocalDateTime modifiedDate;


    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @PrePersist
    protected void onCreate() {
        isDeleted = false;
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedDate = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
