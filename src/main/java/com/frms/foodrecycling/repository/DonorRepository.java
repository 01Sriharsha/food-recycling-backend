package com.frms.foodrecycling.repository;


import com.frms.foodrecycling.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DonorRepository extends JpaRepository<Donor , Integer> {

    Optional<Donor> findByEmail(String email);
}
