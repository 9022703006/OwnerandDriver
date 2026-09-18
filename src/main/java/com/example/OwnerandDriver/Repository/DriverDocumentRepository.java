package com.example.OwnerandDriver.Repository;

import com.example.OwnerandDriver.Model.Driver_Documents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverDocumentRepository
        extends JpaRepository<Driver_Documents, Long> {

    List<Driver_Documents> findByDriver_Id(Long driverId);
}