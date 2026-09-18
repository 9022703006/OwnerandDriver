package com.example.OwnerandDriver.Services;

import com.example.OwnerandDriver.Model.Owner_Reg;
import com.example.OwnerandDriver.Repository.Owner_Repository;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class Owner_Services {
    private final Owner_Repository ownerRepository;
    private final SecureRandom secureRandom = new SecureRandom();

    public Owner_Services(Owner_Repository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public String Owner_Register(Owner_Reg owner){
        Optional<Owner_Reg> checkemail = ownerRepository.findByEmail(owner.getEmail());
        if(checkemail.isPresent()){
            return "Email is already Present!";
        }
        if(!owner.getPassword().equals(owner.getConformpassword())){
            return "Password and Conform Password did not match!";
        }
        // generated opt
        String otp = String.valueOf(100000 + secureRandom.nextInt(900000));
        //set opt
        owner.setOtp(otp);
        // opt is expired after 5 min
        owner.setOtpExpiry(
                LocalDateTime.now().plusMinutes(5)
        );
        owner.setVerified(false);
        owner.setActive(false);
        ownerRepository.save(owner);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        String time = owner.getCreateAt().format(formatter);
        return "Owner register successfully"+owner.getFullname()+"Date & Time:"+owner.getCreateAt();
    }
}
