package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.RequestFood;
import com.frms.foodrecycling.service.RequestFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class RequestFoodController {

    @Autowired
    private RequestFoodService requestFoodService;

    @GetMapping("/request-food")
    public ResponseEntity<?> retrieveAllRequestedFoods(){
        return new ResponseEntity<>(requestFoodService.getAllRequestedFoods() , HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/request-food")
    public ResponseEntity<?> retrieveAllRequestedFoodsByMember(@PathVariable Integer memberId){
        return new ResponseEntity<>(requestFoodService.getAllRequestedFoodsByMember(memberId) , HttpStatus.OK);
    }

    @GetMapping("/request-food/{requestId}")
    public ResponseEntity<?> retrieveSingleRequestedFood(@PathVariable String requestId){
        return new ResponseEntity<>(requestFoodService.getSingleRequestedFood(requestId) , HttpStatus.OK);
    }

    @PostMapping("/members/{memberId}/request-food")
    public ResponseEntity<?> createRequestedFood(@PathVariable Integer memberId , @RequestBody RequestFood requestFood){
        return new ResponseEntity<>(requestFoodService.createRequest(requestFood , memberId) , HttpStatus.CREATED);
    }

    @PutMapping("/request-food/{requestId}")
    public ResponseEntity<?> updateRequestedFood(@PathVariable String requestId , @RequestBody RequestFood requestFood){
        return new ResponseEntity<>(requestFoodService.updateRequestedFood(requestFood , requestId) , HttpStatus.CREATED);
    }

    @DeleteMapping("/request-food/{requestId}")
    public ResponseEntity<?> removeRequestedFood(@PathVariable String requestId){
        return new ResponseEntity<>("request removed successfully!!", HttpStatus.OK);
    }
}
