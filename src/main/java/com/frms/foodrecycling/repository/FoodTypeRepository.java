package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType , Integer> {
}
