package com.frms.foodrecycling.service;


import com.frms.foodrecycling.entity.Donation;
import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.entity.RequestFood;
import com.frms.foodrecycling.repository.MemberRepository;
import com.frms.foodrecycling.repository.NgoRepository;
import com.frms.foodrecycling.repository.RequestFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RequestFoodService {

    @Autowired
    private RequestFoodRepository requestFoodRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private NgoRepository ngoRepository;

    public List<RequestFood> getAllRequestedFoods() {
        return requestFoodRepository.findAll();
    }

    public List<RequestFood> getAllRequestedFoodsByMember(Integer memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        return requestFoodRepository.findByMember(member);
    }

    public RequestFood getSingleRequestedFood(String requestId) {
        return requestFoodRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("No food requested with the provided id"));
    }

    public RequestFood createRequest(RequestFood requestFood, Integer memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member Not Found"));
        requestFood.setDateTime(LocalDateTime.now());
        requestFood.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        requestFood.setQuantity(requestFood.getQuantity() + " KG");
        requestFood.setMember(member);
        requestFood.setStatus("pending");
        var savedRequest = requestFoodRepository.save(requestFood);
        System.out.println(savedRequest);
        return savedRequest;
    }

    public RequestFood updateRequestedFood(RequestFood requestFood, String requestId) {
        RequestFood existingRequest = requestFoodRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("No food requested with the provided id"));
        if (!existingRequest.getStatus().equals("pending")) {
            throw new RuntimeException("Request has already been " + existingRequest.getStatus());
        }
        existingRequest.setFoodType(requestFood.getFoodType());
        existingRequest.setAddress(requestFood.getAddress());
        existingRequest.setCity(requestFood.getCity());
        existingRequest.setPhone(requestFood.getPhone());
        existingRequest.setDescription(requestFood.getDescription());
        existingRequest.setQuantity(requestFood.getQuantity());
        existingRequest.setStatus(requestFood.getStatus());
        return requestFoodRepository.save(existingRequest);
    }

    public void deleteRequestedFood(String requestId) {
        RequestFood requestFood = requestFoodRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("No food requested with the provided id"));
        requestFoodRepository.delete(requestFood);
    }

    public void assignNgo(String requestId, Integer NgoId) {
        RequestFood requestFood = requestFoodRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("No food requested with the provided id"));
        NGO ngo = ngoRepository.findById(NgoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));
    }
}
