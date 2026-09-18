package com.example.OwnerandDriver.Controller;

import com.example.OwnerandDriver.Enum.FuelType;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Enum.VehicleType;
import com.example.OwnerandDriver.Model.Vehicle_Management;
import com.example.OwnerandDriver.Services.Vehicle_Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
public class Vehicle_Controller{
    private final Vehicle_Service vehicleService;
    public Vehicle_Controller(Vehicle_Service vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> vehicle_register(@RequestBody Vehicle_Management vehicleManagement){
        String result = vehicleService.Vehicle_data(vehicleManagement);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<?> findnumber (@PathVariable String number){
        Optional<Vehicle_Management> vehicle = vehicleService.findNumber(number);
        if(vehicle.isPresent()){
            return ResponseEntity.ok(vehicle.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Vehicle_Management>> findvehical(@PathVariable Status status){
        return ResponseEntity.ok(vehicleService.findStatus(status));
    }

    @GetMapping("/Fuel/{fuelType}")
    private ResponseEntity<List<Vehicle_Management>> findfuel(@PathVariable FuelType fuelType){
        return ResponseEntity.ok(vehicleService.findFuel(fuelType));
    }

    @GetMapping("/vehicle/{vehicleType}")
    private ResponseEntity<List<Vehicle_Management>> findvehical(@PathVariable VehicleType vehicleType){
        return ResponseEntity.ok(vehicleService.findVehicleType(vehicleType));
    }
    
    @GetMapping("/owner/{fullname}")
    private ResponseEntity<List<Vehicle_Management>> findOwnerCar(@PathVariable String fullname){
        return  ResponseEntity.ok(vehicleService.findOwernCar(fullname));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateVehicle(@PathVariable Long id, @RequestBody Vehicle_Management vehicleManagement){
        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicleManagement));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        return ResponseEntity.ok(vehicleService.deletedVehicle(id));
    }

}
/*

 important because Admin must approve and Availability vehicles
Activate/Deactivate vehicle
Later: RC documents, features, images, etc.*/
