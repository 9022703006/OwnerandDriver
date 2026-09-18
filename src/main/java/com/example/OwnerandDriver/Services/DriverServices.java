package com.example.OwnerandDriver.Services;

import com.example.OwnerandDriver.Enum.DriverStatus;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Model.Driver_Profile;
import com.example.OwnerandDriver.Repository.DriverResository;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.util.List;
import java.util.Optional;

@Service
public class DriverServices {
    private final DriverResository driverResository;

    public DriverServices(DriverResository driverResository) {
        this.driverResository = driverResository;
    }

    public String saveDriver(Driver_Profile driver) {

        Optional<Driver_Profile> existing =
                driverResository.findByLicenseNumber(driver.getLicenseNumber());

        if (existing.isPresent()) {
            return "Driver with this license number already exists";
        }

        driver.setApprovalStatus(Status.PENDING);
        driver.setStatus(DriverStatus.INACTIVE);
        driver.setTotalTrips(0);
        driver.setWillingToDrive(true);

        driverResository.save(driver);
        return "Driver registered successfully";
    }

    public Driver_Profile getbyId(Long id){
        return driverResository.findById(id).orElseThrow(()->new RuntimeException("Driver not Found"));
    }

    public Driver_Profile getbyLicenseNumber(String LicenseNumber){
        return driverResository.findByLicenseNumber(LicenseNumber).orElseThrow(()-> new RuntimeException("Driver is not found"));
    }

    public List<Driver_Profile> getallDriver(){
        return driverResository.findAll();
    }

    public List<Driver_Profile> getbystatus(){
        return driverResository.findByStatusAndApprovalStatus(DriverStatus.AVAILABLE,Status.APPROVED);
    }

    public Driver_Profile updateDriver(Long id,Driver_Profile driverProfile){
        Driver_Profile driver = driverResository.findById(id).orElseThrow(()->new RuntimeException("Driver not found"));
        driver.setLicenseNumber(driverProfile.getLicenseNumber());
        driver.setLicenseExpiryDate(driverProfile.getLicenseExpiryDate());
        driver.setYearsOfExperience(driverProfile.getYearsOfExperience());
        driver.setWillingToDrive(driverProfile.getWillingToDrive());

        return driverResository.save(driverProfile);
    }

    public String deletedDriver(Long id){
        if(!driverResository.existsById(id)){
            throw new RuntimeException("Driver is not Found!");
        }
        driverResository.deleteById(id);
        return "Driver deleted successfully";
    }

}
