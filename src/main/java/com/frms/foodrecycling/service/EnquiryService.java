package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Enquiry;
import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.repository.EnquiryRepository;
import com.frms.foodrecycling.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Enquiry> getAllEnquiry() {
        return enquiryRepository.findAll();
    }

    public List<Enquiry> getAllEnquiryByMember(Integer memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        return enquiryRepository.findByMember(member);
    }

    public Enquiry createNewEnquiry(Enquiry enquiry , Integer memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        enquiry.setId(UUID.randomUUID().toString().split("-")[0].toUpperCase());
        enquiry.setDate(LocalDate.now());
        enquiry.setStatus("pending");
        enquiry.setMember(member);
        return enquiryRepository.save(enquiry);
    }

    public Enquiry updateEnquiry(Enquiry enquiry, String enquiryId) {
        Enquiry existingEnquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
        if(!existingEnquiry.getStatus().equals("pending")){
            throw new RuntimeException("Cannot perform action! Enquiry already got replied!!");
        }
        existingEnquiry.setDate(LocalDate.now());
        existingEnquiry.setMessage(enquiry.getMessage());
        existingEnquiry.setStatus(enquiry.getStatus());
        existingEnquiry.setReply(enquiry.getReply());
        return enquiryRepository.save(existingEnquiry);
    }

    public void deleteEnquiry(String enquiryId) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found"));
        if(!enquiry.getStatus().equals("pending")){
            throw new RuntimeException("Cannot perform action! Enquiry already got replied!!");
        }
        enquiryRepository.delete(enquiry);
    }
}
