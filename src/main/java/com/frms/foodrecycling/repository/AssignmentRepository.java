package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {

    List<Assignment> findByNgoId(Integer NgoId);
}
