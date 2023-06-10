package com.frms.foodrecycling.service;


import com.frms.foodrecycling.entity.Donation;
import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.repository.DonationRepository;
import com.frms.foodrecycling.repository.DonorRepository;
import com.frms.foodrecycling.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private  NgoRepository ngoRepository;

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation getSingleDonation(String donationId) {
        return donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
    }

    public Donation createDonation(Donation donation, Integer donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        donation.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        donation.setDate(LocalDate.now());
        donation.setDonor(donor);
        return donationRepository.save(donation);
    }

    public Donation updateDonation(Donation donation, String donationId) {
        Donation existingDonation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        if (!existingDonation.getStatus().equals("pending")) {
            throw new RuntimeException("Donation has already been " + existingDonation.getStatus());
        }
        existingDonation.setDate(LocalDate.now());
        existingDonation.setDonorType(donation.getDonorType());
        existingDonation.setDonorName(donation.getDonorName());
        existingDonation.setFoodType(donation.getFoodType());
        existingDonation.setFoodItems(donation.getFoodItems());
        existingDonation.setArea(donation.getArea());
        existingDonation.setAddress(donation.getAddress());
        existingDonation.setCity(donation.getCity());
        existingDonation.setStatus(donation.getStatus());   //for admin approval
        return donationRepository.save(existingDonation);
    }

    public void deleteDonation(String donationId) {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        donationRepository.delete(donation);
    }

    public List<Donation> getAllDonationsByDonor(Integer donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return donationRepository.findByDonor(donor);
    }

    public void uploadImage(String donationId , MultipartFile image) throws IOException {
        Donation donation = donationRepository.findById(donationId)
                .orElseThrow(() -> new RuntimeException("Donation not found"));
        donation.setImage(image.getBytes());
        donation.setImageName(image.getOriginalFilename());
        donationRepository.save(donation);
    }
}
