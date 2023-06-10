package com.frms.foodrecycling.service;

import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getSingleMember(Integer memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Member updateMember(Member member , Integer memberId){
        Member existingMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingMember.setName(member.getName());
        existingMember.setEmail(member.getEmail());
        existingMember.setPhone(member.getPhone());
        existingMember.setAddress(member.getAddress());
        existingMember.setCity(member.getCity());
        existingMember.setPassword(member.getPassword());
        return memberRepository.save(existingMember);
    }

    public void deleteMember(Integer memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        memberRepository.delete(member);
    }
}
