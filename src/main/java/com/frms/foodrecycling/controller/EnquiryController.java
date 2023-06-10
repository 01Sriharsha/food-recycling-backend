package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.Enquiry;
import com.frms.foodrecycling.service.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/enquiries")
    public ResponseEntity<?> retrieveAllEnquiries(){
        return new ResponseEntity<>(enquiryService.getAllEnquiry() , HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}/enquiries")
    public ResponseEntity<?> retrieveAllEnquiriesByMember(@PathVariable Integer memberId ){
        return new ResponseEntity<>(enquiryService.getAllEnquiryByMember(memberId) , HttpStatus.OK);
    }
    @PostMapping("/members/{memberId}/enquiries")
    public ResponseEntity<?> createNewEnquiry(@PathVariable Integer memberId , @RequestBody Enquiry enquiry){
        return new ResponseEntity<>(enquiryService.createNewEnquiry(enquiry , memberId) , HttpStatus.CREATED);
    }

    @PutMapping("/enquiries/{enquiryId}")
    public ResponseEntity<?> updateEnquiry(@PathVariable String enquiryId , @RequestBody Enquiry enquiry){
        return new ResponseEntity<>(enquiryService.updateEnquiry(enquiry , enquiryId) , HttpStatus.CREATED);
    }

    @DeleteMapping("/enquiries/{enquiryId}")
    public ResponseEntity<?> deleteEnquiry(@PathVariable String enquiryId) {
        enquiryService.deleteEnquiry(enquiryId);
        return new ResponseEntity<>("Enquiry removed successfully!!", HttpStatus.OK);
    }
}
