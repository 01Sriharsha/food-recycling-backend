package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.NGO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NgoRepository extends JpaRepository<NGO , Integer> {

    Optional<NGO> findByEmail(String email);
}
