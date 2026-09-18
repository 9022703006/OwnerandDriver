package com.example.OwnerandDriver.Controller;

import com.example.OwnerandDriver.Model.Owner_Reg;
import com.example.OwnerandDriver.Repository.Owner_Repository;
import com.example.OwnerandDriver.Services.Owner_Services;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@CrossOrigin("")
public class OwnerController {
    private final Owner_Services ownerServices;
    private final Owner_Repository ownerRepository;

    public OwnerController(Owner_Services ownerServices, Owner_Repository ownerRepository) {
        this.ownerServices = ownerServices;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/test")
    public String test() {
        return "Owner API is working!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> ownerregister(@RequestBody Owner_Reg owner){
        String owner_data = ownerServices.Owner_Register(owner);
        return ResponseEntity.ok("Register is Successfully....!"+owner_data);
    }
}
