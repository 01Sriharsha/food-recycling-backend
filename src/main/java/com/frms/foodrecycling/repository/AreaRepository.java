package com.frms.foodrecycling.repository;

import com.frms.foodrecycling.entity.Area;
import com.frms.foodrecycling.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    List<Area> findByCity(City city);

    boolean existsByName(String name);
}
