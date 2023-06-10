package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.entity.Admin;
import com.frms.foodrecycling.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Admin admin) {

        Admin adminData = adminRepository.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("User Not Found!!"));
        if (!adminData.getPassword().equals(admin.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }
        Map<String, String> response = new HashMap<>();
        response.put("user", "admin");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
