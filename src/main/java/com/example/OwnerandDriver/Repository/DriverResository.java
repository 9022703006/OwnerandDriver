package com.example.OwnerandDriver.Repository;

import com.example.OwnerandDriver.Enum.DriverStatus;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Model.Driver_Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverResository extends JpaRepository<Driver_Profile,Long>{
    Optional<Driver_Profile> findByLicenseNumber(String licenseNumber);

    List<Driver_Profile> findByStatus(DriverStatus status);

    List<Driver_Profile> findByStatusAndApprovalStatus(
            DriverStatus status,
            Status approvalStatus
    );
}
