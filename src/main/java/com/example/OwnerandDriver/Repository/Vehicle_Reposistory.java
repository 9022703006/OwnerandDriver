package com.example.OwnerandDriver.Repository;
import com.example.OwnerandDriver.Enum.FuelType;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Enum.VehicleType;
import com.example.OwnerandDriver.Model.Vehicle_Management;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Vehicle_Reposistory extends JpaRepository<Vehicle_Management,Long> {

    Optional<Vehicle_Management> findByVehicleNumber(String vehicleNumber);

    @Query("SELECT v FROM Vehicle_Management v WHERE v.ownerReg.fullname = :fullname")
    List<Vehicle_Management> findByOwnerReg_Fullname(@Param("fullname") String fullname);

    @Query("SELECT v FROM Vehicle_Management v WHERE v.status = :status")
    List<Vehicle_Management> findByStatus(@Param("status") Status status);

    @Query("SELECT v FROM Vehicle_Management v WHERE v.vehicleType = :vehicleType")
    List<Vehicle_Management> findByVehicleType(
            @Param("vehicleType") VehicleType vehicleType);

    @Query("SELECT v FROM Vehicle_Management v WHERE v.fuelType = :fuelType")
    List<Vehicle_Management> findByFuelType(
            @Param("fuelType") FuelType fuelType);
}