package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.entity.ShoutOut;
import com.frms.foodrecycling.repository.DonorRepository;
import com.frms.foodrecycling.repository.ShoutOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ShoutOutService {

    @Autowired
    private ShoutOutRepository shoutOutRepository;
    @Autowired
    private DonorRepository donorRepository;

    public List<ShoutOut> getAllShoutOuts() {
        return shoutOutRepository.findAll();
    }

    public List<ShoutOut> getAllShoutOutsByDonor(Integer donorId) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor Not Found"));
        return shoutOutRepository.findByDonor(donor);
    }

    public ShoutOut getSingleShoutOut(String shoutOutId){
        return shoutOutRepository.findById(shoutOutId)
                .orElseThrow(() -> new RuntimeException("Shout Out Not Found"));
    }

    public ShoutOut createNewShoutOut(ShoutOut shoutOut) {
        shoutOut.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        shoutOut.setDate(LocalDate.now());
        shoutOut.setResponded(false);
        return shoutOutRepository.save(shoutOut);
    }

    public ShoutOut updateShoutOut(String shoutOutId, Integer donorId, ShoutOut shoutOut) {
        Donor donor = donorRepository.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor Not Found"));
        ShoutOut existingShoutOut = shoutOutRepository.findById(shoutOutId)
                .orElseThrow(() -> new RuntimeException("Shout Out Not Found"));
        existingShoutOut.setDate(LocalDate.now());
        existingShoutOut.setFoodItem(shoutOut.getFoodItem());
        existingShoutOut.setQuantity(shoutOut.getQuantity());
        existingShoutOut.setFoodType(shoutOut.getFoodType());
        existingShoutOut.setResponded(shoutOut.isResponded());
        existingShoutOut.setDonor(donor);
        return shoutOutRepository.save(existingShoutOut);
    }

    public void deleteShoutOut(String shoutOutId) {
        ShoutOut shoutOut = shoutOutRepository.findById(shoutOutId)
                .orElseThrow(() -> new RuntimeException("Shout Out Not Found"));
        if(!shoutOut.isResponded()){
            shoutOutRepository.delete(shoutOut);
        }else{
            throw new RuntimeException("Shout out already been responded");
        }

    }
}
