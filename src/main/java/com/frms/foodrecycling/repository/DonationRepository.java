package com.frms.foodrecycling.repository;


import com.frms.foodrecycling.entity.Donation;
import com.frms.foodrecycling.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation , String> {

    List<Donation> findByDonor(Donor donor);
}
