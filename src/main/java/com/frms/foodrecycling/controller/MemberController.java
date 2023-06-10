package com.frms.foodrecycling.controller;


import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/members")
    public ResponseEntity<?> retrieveAllMembers(){
        return new ResponseEntity<>(memberService.getAllMembers() , HttpStatus.OK);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> retrieveSingleMember(@PathVariable Integer memberId){
        return new ResponseEntity<>(memberService.getSingleMember(memberId) , HttpStatus.OK);
    }

    @PostMapping("/members")
    public ResponseEntity<?> registerNewMember(@RequestBody Member member ){
        return new ResponseEntity<>(memberService.createMember(member) , HttpStatus.CREATED);
    }

    @PutMapping("/members/{memberId}")
    public ResponseEntity<?> updateTheMember(@RequestBody Member member ,  @PathVariable Integer memberId){
        return new ResponseEntity<>(memberService.updateMember(member , memberId) , HttpStatus.CREATED);

    }

    @DeleteMapping("/members/{memberId}")
    public ResponseEntity<?> removeTheMember(@PathVariable Integer memberId){
        memberService.deleteMember(memberId);
        return new ResponseEntity<>("Member removed successfully!!" , HttpStatus.OK);
    }
}
