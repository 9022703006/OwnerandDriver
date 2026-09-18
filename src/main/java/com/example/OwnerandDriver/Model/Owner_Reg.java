package com.example.OwnerandDriver.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.aot.generate.GeneratedTypeReference;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@Table(name = "Owner_Register")
public class Owner_Reg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;
    @Column(name = "fullname" ,nullable = false,length = 25)
    private String fullname;
    @Column(name = "Moblie_number",nullable = false)
    private String number;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    private String password;
    @Transient
    private String conformpassword;

    @Column(length = 6)
    private String otp;
    private LocalDateTime otpExpiry;
    @Column(nullable = false)
    private boolean isVerified = false;

    @Column(nullable = false)
    private boolean isActive = false;

    @Column(name="CreateAT",nullable = false)
    private LocalDateTime CreateAt;
    @Column(name = "UpdateAT",nullable = false)
    private LocalDateTime UpdateAt;

    @OneToMany(mappedBy = "ownerReg")
    @JsonIgnore
    private List<Vehicle_Management> vehicleManagements;

    @PrePersist
    protected void onCreate(){
        CreateAt = LocalDateTime.now();
        UpdateAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        UpdateAt = LocalDateTime.now();
    }
}
