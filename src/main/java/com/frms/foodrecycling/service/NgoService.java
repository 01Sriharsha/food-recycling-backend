package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NgoService {

    @Autowired
    private NgoRepository ngoRepository;

    public NGO createNGO(NGO ngo) {
        ngo.setStatus("pending");
        return ngoRepository.save(ngo);
    }

    public List<NGO> getAllNGOs() {
        return ngoRepository.findAll();
    }

    public NGO getSingleNGO(Integer ngoId) {
        return ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public NGO updateNGO(NGO ngo , Integer ngoId){
        NGO existingNGO = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if(existingNGO.getStatus().equals("pending")){
            existingNGO.setStatus(ngo.getStatus());
        }
        existingNGO.setName(ngo.getName());
        existingNGO.setEmail(ngo.getEmail());
        existingNGO.setPhone(ngo.getPhone());
        existingNGO.setAddress(ngo.getAddress());
        existingNGO.setCity(ngo.getCity());
        existingNGO.setPassword(ngo.getPassword());
        return ngoRepository.save(existingNGO);
    }

    public void deleteNGO(Integer ngoId){
        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ngoRepository.delete(ngo);
    }

    public void verifyNGO(Integer ngoId){
        NGO ngo = ngoRepository.findById(ngoId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        ngo.setStatus("verified");
        ngoRepository.save(ngo);
    }
}
