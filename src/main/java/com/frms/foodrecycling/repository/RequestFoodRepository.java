package com.frms.foodrecycling.repository;


import com.frms.foodrecycling.entity.Member;
import com.frms.foodrecycling.entity.RequestFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestFoodRepository extends JpaRepository<RequestFood , String> {

    List<RequestFood> findByMember(Member member);

    boolean existsByMember(Member member);
    
}
