package com.frms.foodrecycling.repository;


import com.frms.foodrecycling.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin , Integer> {
}
