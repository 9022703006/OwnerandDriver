package com.example.OwnerandDriver.Services;

import com.example.OwnerandDriver.Enum.FuelType;
import com.example.OwnerandDriver.Enum.Status;
import com.example.OwnerandDriver.Enum.VehicleType;
import com.example.OwnerandDriver.Model.Vehicle_Management;
import com.example.OwnerandDriver.Repository.Vehicle_Reposistory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Vehicle_Service {

    private final Vehicle_Reposistory vehicleReposistory;

    public Vehicle_Service(Vehicle_Reposistory vehicleReposistory) {
        this.vehicleReposistory = vehicleReposistory;
    }

    public String Vehicle_data(Vehicle_Management vehicleManagement) {

        Optional<Vehicle_Management> numberFound =
                vehicleReposistory.findByVehicleNumber(vehicleManagement.getVehicleNumber());

        if (numberFound.isPresent()) {
            return "Vehicle number already exists!";
        }

        vehicleReposistory.save(vehicleManagement);

        return "Vehicle saved successfully!";
    }

    public Optional<Vehicle_Management> findNumber(String number) {
        return vehicleReposistory.findByVehicleNumber(number);
    }

    public List<Vehicle_Management> findStatus(Status status){
        return vehicleReposistory.findByStatus(status);
    }

    public List<Vehicle_Management> findFuel(FuelType fuelType){
        return vehicleReposistory.findByFuelType(fuelType);
    }

    public List<Vehicle_Management> findVehicleType(VehicleType vehicleType){
        return vehicleReposistory.findByVehicleType(vehicleType);

    }
    public List<Vehicle_Management> findOwernCar(String fullname){
        return  vehicleReposistory.findByOwnerReg_Fullname(fullname);
    }

    public String updateVehicle(Long id , Vehicle_Management vehicleManagement){
        Optional<Vehicle_Management> checkid = vehicleReposistory.findById(id);
        if(!checkid.isPresent()){
            return "Vehicle is not Found";
        }
        Vehicle_Management vehicle = new Vehicle_Management();
        vehicle.setV_name(vehicleManagement.getV_name());
        vehicle.setVehicleNumber(vehicleManagement.getVehicleNumber());
        vehicle.setModel_years(vehicleManagement.getModel_years());
        vehicle.setRegistrationDate(vehicle.getRegistrationDate());
        vehicle.setRegistrationDate(vehicle.getRegistrationDate());
        vehicle.setDailyRate(vehicleManagement.getDailyRate());
        vehicle.setMonthlyRate(vehicleManagement.getMonthlyRate());
        vehicle.setFuelType(vehicleManagement.getFuelType());
        vehicle.setStatus(vehicleManagement.getStatus());
        vehicle.setVehicleType(vehicleManagement.getVehicleType());
        vehicle.setOwnerReg(vehicleManagement.getOwnerReg());
        vehicleReposistory.save(vehicle);
        return "Update is successfully";
    }

    public String deletedVehicle(Long id){
        Optional<Vehicle_Management> checkid = vehicleReposistory.findById(id);
        if(checkid.isEmpty()){
            return "Vehicle is not Found";
        }

        vehicleReposistory.deleteById(id);
        Vehicle_Management vehicle = new Vehicle_Management();
        return "Vehicle is delected successfully"+ vehicle.getV_name();
    }

}
