package com.example.OwnerandDriver.Controller;

import com.example.OwnerandDriver.Model.Driver_Profile;
import com.example.OwnerandDriver.Services.DriverServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/driver")
public class Driver_Controller {

    private final DriverServices driverServices;

    public Driver_Controller(DriverServices driverServices) {
        this.driverServices = driverServices;
    }

    @PostMapping
    public ResponseEntity<?> saveDriver(@RequestBody Driver_Profile driverProfile){
        String data = driverServices.saveDriver(driverProfile);
        return ResponseEntity.ok(data);

    }

    @GetMapping
    public ResponseEntity<?> getAllDrivers() {
        return ResponseEntity.ok(driverServices.getallDriver());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(driverServices.getbyId(id));
    }

    @GetMapping("/license/{licenseNumber}")
    public ResponseEntity<?> findByLicenseNumber(
            @PathVariable String licenseNumber) {

        return ResponseEntity.ok(
                driverServices.getbyLicenseNumber(licenseNumber)
        );
    }

    @GetMapping("/available")
    public ResponseEntity<?> findAvailableDrivers() {
        return ResponseEntity.ok(driverServices.getbystatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDriver(
            @PathVariable Long id,
            @RequestBody Driver_Profile driverProfile) {

        return ResponseEntity.ok(
                driverServices.updateDriver(id, driverProfile)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDriver(@PathVariable Long id) {

        return ResponseEntity.ok(
                driverServices.deletedDriver(id)
        );
    }
}