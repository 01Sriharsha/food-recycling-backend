package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.Area;
import com.frms.foodrecycling.entity.City;
import com.frms.foodrecycling.repository.AreaRepository;
import com.frms.foodrecycling.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AreaController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AreaRepository areaRepository;

    @GetMapping("/areas")
    public ResponseEntity<?> getAllAreas(){
        return new ResponseEntity<>(areaRepository.findAll() , HttpStatus.OK);
    }

    @GetMapping("/areas/{areaId}")
    public ResponseEntity<?> getSingleArea(@PathVariable Integer areaId){
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area Not Found!!"));
        return new ResponseEntity<>(area , HttpStatus.OK);
    }

    @PostMapping("/cities/{cityId}/areas")
    public ResponseEntity<?> createArea(@PathVariable Integer cityId ,  @RequestBody Area area){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City Not Found!!"));
        area.setCity(city);
        return new ResponseEntity<>(areaRepository.save(area) , HttpStatus.CREATED);
    }

    @DeleteMapping("/areas/{areaId}")
    public ResponseEntity<?> deleteArea(@PathVariable Integer areaId){
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new RuntimeException("Area Not Found!!"));
        areaRepository.delete(area);
        return new ResponseEntity<>("Area Removed Successfully!!" , HttpStatus.OK);
    }

    @GetMapping("/cities/{cityId}/areas")
    public ResponseEntity<?> getAreasByCity(@PathVariable Integer cityId){
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new RuntimeException("City Not Found!!"));
        List<Area> areas = areaRepository.findByCity(city);
        return new ResponseEntity<>(areas , HttpStatus.OK);
    }
}
