package com.example.OwnerandDriver.Model;

import com.example.OwnerandDriver.Enum.FuelType;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Enum.VehicleAvailability;
import com.example.OwnerandDriver.Enum.VehicleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Vehicle_Management")
public class Vehicle_Management {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "Vehicle_Name",nullable = false,length = 20)
    private String v_name;
    @Column(name = "Vehicle_Number", nullable = false, unique = true)
    private String vehicleNumber;
    private String model_years;
    private Integer seatingCapacity;
    private LocalDateTime registrationDate;
    private BigDecimal dailyRate;
    private BigDecimal monthlyRate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleAvailability availability;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @ManyToOne
    @JoinColumn(name = "Owner_id",nullable = false)
    private Owner_Reg ownerReg;

    private  LocalDateTime CreateAT;
    private LocalDateTime UpdateAt;

    @PrePersist
    protected  void Create(){
        CreateAT = LocalDateTime.now();
        UpdateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void update(){
        UpdateAt = LocalDateTime.now();
    }

}
