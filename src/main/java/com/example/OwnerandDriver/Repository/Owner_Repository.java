package com.example.OwnerandDriver.Repository;

import com.example.OwnerandDriver.Model.Owner_Reg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Owner_Repository extends JpaRepository<Owner_Reg,Long>{
    Optional<Owner_Reg> findByEmail(String email);
    Optional<Owner_Reg> findByPassword(String password);
}
