package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Assignment;
import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.entity.RequestFood;
import com.frms.foodrecycling.repository.AssignmentRepository;
import com.frms.foodrecycling.repository.NgoRepository;
import com.frms.foodrecycling.repository.RequestFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private NgoRepository ngoRepository;

    @Autowired
    private RequestFoodRepository requestFoodRepository;

    @Autowired
    private EmailService emailService;

    public Assignment createAssignment(Assignment assignment, Integer NgoId, String requestId) {
        NGO ngo = ngoRepository.findById(NgoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));
        RequestFood requestFood = requestFoodRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Requested food not found"));
        assignment.setDeliveryDate(assignment.getDeliveryDate());
        assignment.setAcceptance("pending");
        assignment.setNgo(ngo);
        assignment.setRequestFood(requestFood);
        requestFood.setStatus("assigned");
        requestFood.setNgo(ngo);
        requestFoodRepository.save(requestFood);
        var savedAssignment = assignmentRepository.save(assignment);
        emailService.sendAssignmentMailToNGO(ngo.getEmail(), savedAssignment);
        return savedAssignment;
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public List<Assignment> getAllAssignmentsByNgo(Integer NgoId) {
        NGO ngo = ngoRepository.findById(NgoId)
                .orElseThrow(() -> new RuntimeException("NGO not found"));
        return assignmentRepository.findByNgoId(ngo.getId());
    }

    public Assignment getSingleAssignment(Integer assignmentId) {
        return assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String updateDeliveryStatus(Integer assignmentId) {
        Assignment existingAssignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (existingAssignment.isDelivered()) {
            throw new RuntimeException("Food already delivered and distributed!!");
        }
        existingAssignment.setDelivered(true);
        assignmentRepository.save(existingAssignment);
        return "Delivery status updated successfully!!";
    }

    public String updateAcceptanceStatus(Assignment assignment, Integer assignmentId) {
        Assignment existingAssignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!existingAssignment.getAcceptance().equals("pending")) {
            throw new RuntimeException("Assignment already " + existingAssignment.getAcceptance());
        }
        if (assignment.getAcceptance().equals("rejected")) {
            RequestFood requestFood = requestFoodRepository.findById(existingAssignment.getRequestFood().getId())
                    .orElseThrow(() -> new RuntimeException("Requested food not found"));
            requestFood.setStatus("approved");
            requestFood.setNgo(null);
            existingAssignment.setAcceptance(assignment.getAcceptance());
            assignmentRepository.save(existingAssignment);
            requestFoodRepository.save(requestFood);
            return "Assignment rejected!!";
        } else {
            existingAssignment.setAcceptance(assignment.getAcceptance());
            var savedAssignment = assignmentRepository.save(existingAssignment);
            emailService.sendConfirmationMailToMember(savedAssignment);
            return "Assignment accepted to deliver!!";
        }
    }

    public void deleteAssignment(Integer assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
        if (assignment.getAcceptance().equals("accepted") || assignment.getAcceptance().equals("rejected")) {
            throw new RuntimeException("Assignment already " + assignment.getAcceptance());
        }
        RequestFood requestFood = requestFoodRepository.findById(assignment.getRequestFood().getId())
                .orElseThrow(() -> new RuntimeException("Requested food not found"));
        requestFood.setStatus("approved");
        requestFood.setNgo(null);
        requestFoodRepository.save(requestFood);
        assignmentRepository.delete(assignment);
    }
}
