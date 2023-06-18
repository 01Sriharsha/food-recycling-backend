package com.frms.foodrecycling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RequestFood {

    @Id
    private String id;

    private LocalDateTime dateTime;

    private String foodType;

    private String quantity;

    private String city;

    private String address;

    private String phone;

    private String description;

    private String status;

    @ManyToOne
    private Member member;

    @OneToOne
    private NGO ngo;
}
