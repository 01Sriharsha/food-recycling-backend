package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.FoodType;
import com.frms.foodrecycling.repository.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/v1")
public class FoodTypeController {

    @Autowired
    private  FoodTypeRepository foodTypeRepository;

    @PostMapping("/food-types")
    public ResponseEntity<?> createNewFoodType(@RequestBody FoodType foodType){
        return new ResponseEntity<>(foodTypeRepository.save(foodType) , HttpStatus.CREATED);
    }

    @GetMapping("/food-types")
    public ResponseEntity<?> getAllFoodTypes(){
        return new ResponseEntity<>(foodTypeRepository.findAll() , HttpStatus.OK);
    }

    @PutMapping("/food-types/{foodTypeId}")
    public ResponseEntity<?> deleteFoodType(@PathVariable Integer foodTypeId  , @RequestBody FoodType foodType){
        FoodType existingFoodType = foodTypeRepository.findById(foodTypeId).orElseThrow(() -> new RuntimeException("Food Type Not Found"));
        existingFoodType.setFoodType(foodType.getFoodType());
        return new ResponseEntity<>(foodTypeRepository.save(existingFoodType)  , HttpStatus.CREATED);
    }

    @DeleteMapping("/food-types/{foodTypeId}")
    public ResponseEntity<?> deleteFoodType(@PathVariable Integer foodTypeId){
        foodTypeRepository.deleteById(foodTypeId);
        return new ResponseEntity<>("Food Type Removed successfully!!" , HttpStatus.OK);
    }
}
