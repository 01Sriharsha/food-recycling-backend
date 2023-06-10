package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.Enquiry;
import com.frms.foodrecycling.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry, String> {

    List<Enquiry> findByMember(Member member);
}
