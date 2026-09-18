package com.example.OwnerandDriver.Model;

import com.example.OwnerandDriver.Enum.DriverStatus;
import com.example.OwnerandDriver.Enum.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "driver_profiles")
public class Driver_Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licenseNumber;

    private LocalDate licenseExpiryDate;

    @Enumerated(EnumType.STRING)
    private DriverStatus status = DriverStatus.AVAILABLE;

    private String inactiveReason;

    @Enumerated(EnumType.STRING)
    private Status approvalStatus = Status.PENDING;

    private String rejectionReason;

    private LocalDateTime approvedAt;

    private Double rating;

    private Integer totalTrips = 0;

    private Boolean willingToDrive = true;

    private Integer yearsOfExperience = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}