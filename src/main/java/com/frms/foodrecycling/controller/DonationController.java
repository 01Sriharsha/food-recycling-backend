package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.Donation;
import com.frms.foodrecycling.service.DonationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @GetMapping("/donations")
    public ResponseEntity<?> retrieveAllDonations() {

        return new ResponseEntity<>(donationService.getAllDonations(), HttpStatus.OK);
    }

    @GetMapping("/donors/{donorId}/donations")
    public ResponseEntity<?> retrieveAllDonationsByDonor(@PathVariable Integer donorId) {
        return new ResponseEntity<>(donationService.getAllDonationsByDonor(donorId), HttpStatus.OK);
    }

    @GetMapping("/donations/{donationId}")
    public ResponseEntity<?> retrieveSingleDonation(@PathVariable String donationId) {
        return new ResponseEntity<>(donationService.getSingleDonation(donationId), HttpStatus.OK);
    }

    @PostMapping("/donors/{donorId}/donations")
    public ResponseEntity<?> addNewDonation(@RequestBody Donation donation, @PathVariable Integer donorId) {
        return new ResponseEntity<>(donationService.createDonation(donation, donorId), HttpStatus.CREATED);
    }

    @PutMapping("/donations/{donationId}")
    public ResponseEntity<?> updateDonation(@RequestBody Donation donation, @PathVariable String donationId) {
        return new ResponseEntity<>(donationService.updateDonation(donation, donationId), HttpStatus.CREATED);
    }

    @DeleteMapping("/donations/{donationId}")
    public ResponseEntity<?> removeDonation(@PathVariable String donationId) {
        donationService.deleteDonation(donationId);
        return new ResponseEntity<>("Donation Removed successfully!!", HttpStatus.OK);
    }

    @PostMapping("/donations/{donationId}/upload/image")
    public ResponseEntity<?> uploadFoodImage(
            @RequestParam("image") MultipartFile image,
            @PathVariable String donationId
    ) throws IOException {
        donationService.uploadImage(donationId, image);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/donations/{donationId}/download/images")
    public ResponseEntity<?> downloadFoodImage1(
            @PathVariable String donationId,
            HttpServletResponse response
    ) throws IOException {
        Donation donation = donationService.getSingleDonation(donationId);
        byte[] image = donation.getImage();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentDispositionFormData("image", donation.getImageName());
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}
