package com.frms.foodrecycling.controller;

import com.frms.foodrecycling.dto.LoginDto;
import com.frms.foodrecycling.dto.PasswordDto;
import com.frms.foodrecycling.entity.Admin;
import com.frms.foodrecycling.entity.Donor;
import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.entity.NGO;
import com.frms.foodrecycling.repository.AdminRepository;
import com.frms.foodrecycling.repository.DonorRepository;
import com.frms.foodrecycling.repository.MemberRepository;
import com.frms.foodrecycling.repository.NgoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private NgoRepository ngoRepository;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private MemberRepository memberRepository;


    @PostMapping("/user/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto) {

        // Authenticate Admin
        if (loginDto.getUserType().equals("admin")) {
            Admin admin = adminRepository.findById(Integer.parseInt(loginDto.getUserId()))
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (admin.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>("admin", HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        // Authenticate NGO
        else if (loginDto.getUserType().equals("ngo")) {
            NGO ngo = ngoRepository.findByEmail(loginDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (ngo.getPassword().equals(loginDto.getPassword())) {
                if(ngo.getStatus().equals("pending")){
                    throw new RuntimeException("Your account is currently pending verification!!Try again later!");
                }
                return new ResponseEntity<>(ngo, HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        // Authenticate Donor
        else if (loginDto.getUserType().equals("donor")) {
            Donor donor = donorRepository.findByEmail(loginDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (donor.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>(donor, HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        }
        // Authenticate Member
        else if (loginDto.getUserType().equals("member")) {
            Member member = memberRepository.findByEmail(loginDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            if (member.getPassword().equals(loginDto.getPassword())) {
                return new ResponseEntity<>(member, HttpStatus.OK);
            } else {
                throw new RuntimeException("Invalid Password");
            }
        } else {
            throw new RuntimeException("Select an user type!!");
        }
    }

    @PostMapping("/user/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody PasswordDto passwordDto) {
        if (passwordDto.getUserType().equals("donor")) {
            Donor donor = donorRepository.findByEmail(passwordDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            donor.setPassword(passwordDto.getNewPassword());
            donorRepository.save(donor);
            return new ResponseEntity<>("Password changed" , HttpStatus.OK);
        } else if (passwordDto.getUserType().equals("member")) {
            Member member = memberRepository.findByEmail(passwordDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            member.setPassword(passwordDto.getNewPassword());
            memberRepository.save(member);
            return new ResponseEntity<>("Password changed" , HttpStatus.OK);
        } else {
            throw new RuntimeException("Select an user type!!");
        }

    }
}
