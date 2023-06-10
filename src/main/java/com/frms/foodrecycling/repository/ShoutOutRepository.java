package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.entity.ShoutOut;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoutOutRepository extends JpaRepository<ShoutOut , String> {

    List<ShoutOut> findByDonor(Donor donor);
}
