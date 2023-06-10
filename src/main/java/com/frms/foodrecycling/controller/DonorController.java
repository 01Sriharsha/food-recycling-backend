package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @GetMapping("/donors")
    public ResponseEntity<?> retrieveAllDonors(){
        return new ResponseEntity<>(donorService.getAllDonors() , HttpStatus.OK);
    }

    @GetMapping("/donors/{donorId}")
    public ResponseEntity<?> retrieveSingleDonor(@PathVariable Integer donorId){
        return new ResponseEntity<>(donorService.getSingleDonor(donorId) , HttpStatus.OK);
    }

    @PostMapping("/donors")
    public ResponseEntity<?> registerNewDonor(@RequestBody Donor donor ){
        return new ResponseEntity<>(donorService.createDonor(donor) , HttpStatus.CREATED);
    }

    @PutMapping("/donors/{donorId}")
    public ResponseEntity<?> updateTheDonor(@RequestBody Donor donor ,  @PathVariable Integer donorId){
        return new ResponseEntity<>(donorService.updateDonor(donor , donorId) , HttpStatus.CREATED);

    }

    @DeleteMapping("/donors/{donorId}")
    public ResponseEntity<?> removeTheDonor(@PathVariable Integer donorId){
        donorService.deleteDonor(donorId);
        return new ResponseEntity<>("Donor removed successfully!!" , HttpStatus.OK);
    }

    @PutMapping("/donors/{donorId}/verify")
    public ResponseEntity<?> verifyTheDonor(@RequestBody String status ,  @PathVariable Integer donorId){
        donorService.verifyDonor(status , donorId);
        return new ResponseEntity<>("Donor verified successfully!!" , HttpStatus.CREATED);
    }
}
