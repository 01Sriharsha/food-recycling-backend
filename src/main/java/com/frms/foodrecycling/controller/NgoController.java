package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class NgoController {

    @Autowired
    private NgoService ngoService;

    @GetMapping("/ngos")
    public ResponseEntity<?> retrieveAllNGOs(){
        return new ResponseEntity<>(ngoService.getAllNGOs() , HttpStatus.OK);
    }

    @GetMapping("/ngos/{ngoId}")
    public ResponseEntity<?> retrieveSingleNGO(@PathVariable Integer ngoId){
        return new ResponseEntity<>(ngoService.getSingleNGO(ngoId) , HttpStatus.OK);
    }

    @PostMapping("/ngos")
    public ResponseEntity<?> registerNewNGO(@RequestBody NGO ngo ){
        return new ResponseEntity<>(ngoService.createNGO(ngo) , HttpStatus.CREATED);
    }

    @PutMapping("/ngos/{ngoId}")
    public ResponseEntity<?> updateTheNGO(@RequestBody NGO ngo ,  @PathVariable Integer ngoId){
        return new ResponseEntity<>(ngoService.updateNGO(ngo , ngoId) , HttpStatus.CREATED);

    }

    @DeleteMapping("/ngos/{ngoId}")
    public ResponseEntity<?> removeTheNGO(@PathVariable Integer ngoId){
        ngoService.deleteNGO(ngoId);
        return new ResponseEntity<>("NGO removed successfully!!" , HttpStatus.OK);
    }

    @PutMapping("/ngos/{ngoId}/verify")
    public ResponseEntity<?> verifyTheNGO(@PathVariable Integer ngoId){
        ngoService.verifyNGO(ngoId);
        return new ResponseEntity<>("NGO verified successfully!!" , HttpStatus.CREATED);
    }
}
