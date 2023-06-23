package com.frms.foodrecycling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String deliveryDate;

    private String deliveryAddress;

    private String pickupAddress;

    private String foodItems;

    private boolean delivered;

    private String acceptance;

    @OneToOne(cascade = CascadeType.DETACH)
    private RequestFood requestFood;

    @OneToOne
    private Donor donor;

    @ManyToOne
    private NGO ngo;
}
