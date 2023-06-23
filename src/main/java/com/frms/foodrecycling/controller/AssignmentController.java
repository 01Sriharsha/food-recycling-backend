package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.Assignment;
import com.frms.foodrecycling.service.AssignmentService;
import com.frms.foodrecycling.service.NgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    @GetMapping("/assignments")
    public ResponseEntity<?> retrieveAllAssignments() {
        return new ResponseEntity<>(assignmentService.getAllAssignments(), HttpStatus.OK);
    }

    @GetMapping("/ngo/{NgoId}/assignments")
    public ResponseEntity<?> retrieveAllAssignmentsByNgo(@PathVariable Integer NgoId) {
        return new ResponseEntity<>(assignmentService.getAllAssignmentsByNgo(NgoId), HttpStatus.OK);
    }

    @GetMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> retrieveSingleAssignment(@PathVariable Integer assignmentId) {
        return new ResponseEntity<>(assignmentService.getSingleAssignment(assignmentId), HttpStatus.OK);
    }

    @PostMapping("/request/{requestId}/ngos/{NgoId}/donor/{donorId}/assignments")
    public ResponseEntity<?> registerNewAssignment(
            @RequestBody Assignment assignment,
            @PathVariable Integer NgoId,
            @PathVariable Integer donorId,
            @PathVariable String requestId
    ) {
        return new ResponseEntity<>(assignmentService.createAssignment(assignment, NgoId, requestId, donorId), HttpStatus.CREATED);
    }

    @PutMapping("/assignments/{assignmentId}/update/acceptance")
    public ResponseEntity<?> updateAcceptanceStatus(@RequestBody Assignment assignment, @PathVariable Integer assignmentId) {
        return new ResponseEntity<>(assignmentService.updateAcceptanceStatus(assignment, assignmentId), HttpStatus.CREATED);
    }

    @PutMapping("/assignments/{assignmentId}/update/delivery")
    public ResponseEntity<?> updateDeliveryStatus(@PathVariable Integer assignmentId) {
        return new ResponseEntity<>(assignmentService.updateDeliveryStatus(assignmentId), HttpStatus.CREATED);

    }

    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<?> removeTheAssignment(@PathVariable Integer assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return new ResponseEntity<>("Assignment removed successfully!!", HttpStatus.OK);
    }
}
