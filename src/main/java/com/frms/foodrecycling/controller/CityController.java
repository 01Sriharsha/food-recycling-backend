package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.City;
import com.frms.foodrecycling.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @GetMapping("/cities")
    public ResponseEntity<?> getAllCities(){
        return new ResponseEntity<>(cityRepository.findAll() , HttpStatus.OK);
    }

    @GetMapping("/cities/{cityId}")
    public ResponseEntity<?> getSingleCity(@PathVariable Integer cityId){
        var city = cityRepository.findById(cityId).orElseThrow(()->new RuntimeException("City not found"));
        return new ResponseEntity<>(city , HttpStatus.OK);
    }

    @PostMapping("/cities")
    public ResponseEntity<?> createCity(@RequestBody City city){
        if(cityRepository.existsByName(city.getName())){
            throw new RuntimeException("City already exists!!");
        }
        return new ResponseEntity<>(cityRepository.save(city) , HttpStatus.CREATED);
    }

    @DeleteMapping("/cities/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityId){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City Not Found!!"));
        cityRepository.delete(city);
        return new ResponseEntity<>("City Removed Successfully!!" , HttpStatus.OK);
    }



}
