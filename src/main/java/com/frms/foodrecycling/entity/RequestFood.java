package com.frms.foodrecycling.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestFood {

    @Id
    private String id;

    private String foodType;

    private String quantity;

    private String city;

    private String address;

    private String phone;

    private String description;

    private String status;

    @OneToOne
    private Member member;

    @OneToOne
    private NGO ngo;
}