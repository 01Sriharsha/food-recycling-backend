package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.ShoutOut;
import com.frms.foodrecycling.service.ShoutOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class ShoutOutController {

    @Autowired
    private ShoutOutService shoutOutService;

    @GetMapping("/shoutouts")
    public ResponseEntity<?> retrieveAllShoutOuts() {
        return new ResponseEntity<>(shoutOutService.getAllShoutOuts(), HttpStatus.OK);
    }
    @GetMapping("/donors/{donorId}/shoutouts")
    public ResponseEntity<?> retrieveAllShoutOutsByDonor(Integer donorId) {
        return new ResponseEntity<>(shoutOutService.getAllShoutOutsByDonor(donorId), HttpStatus.OK);
    }

    @GetMapping("/shoutouts/{shoutOutId}")
    public ResponseEntity<?> retrieveSingleShoutOut(@PathVariable String shoutOutId) {
        return new ResponseEntity<>(shoutOutService.getSingleShoutOut(shoutOutId), HttpStatus.OK);
    }

    @PostMapping("/shoutouts")
    public ResponseEntity<?> createNewShoutOut(@RequestBody ShoutOut shoutOut) {
        return new ResponseEntity<>(shoutOutService.createNewShoutOut(shoutOut), HttpStatus.CREATED);
    }

    @PutMapping("/donors/{donorId}/shoutouts/{shoutOutId}")
    public ResponseEntity<?> updateShoutOut(
            @RequestBody ShoutOut shoutOut,
            @PathVariable String shoutOutId,
            @PathVariable Integer donorId
    ) {
        return new ResponseEntity<>(shoutOutService.updateShoutOut(shoutOutId, donorId, shoutOut), HttpStatus.CREATED);
    }

    @DeleteMapping("/shoutouts/{shoutOutId}")
    public ResponseEntity<?> removeShoutOut(@PathVariable String shoutOutId) {
        shoutOutService.deleteShoutOut(shoutOutId);
        return new ResponseEntity<>("shout out removed successfully!!", HttpStatus.OK);
    }

}
