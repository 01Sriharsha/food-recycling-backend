package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor createDonor(Donor donor) {
        donor.setStatus("pending");
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public Donor getSingleDonor(Integer donorId) {
        return donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Donor updateDonor(Donor donor , Integer donorId){
        Donor existingDonor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(existingDonor.getStatus().equals("pending")){
            existingDonor.setStatus(donor.getStatus());
        }
        existingDonor.setName(donor.getName());
        existingDonor.setEmail(donor.getEmail());
        existingDonor.setPhone(donor.getPhone());
        existingDonor.setAddress(donor.getAddress());
        existingDonor.setCity(donor.getCity());
        existingDonor.setPassword(donor.getPassword());
        return donorRepository.save(existingDonor);
    }

    public void deleteDonor(Integer donorId){
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        donorRepository.delete(donor);
    }

    public Donor verifyDonor(String status , Integer donorId){
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        donor.setStatus(status);
        return donorRepository.save(donor);
    }
}
