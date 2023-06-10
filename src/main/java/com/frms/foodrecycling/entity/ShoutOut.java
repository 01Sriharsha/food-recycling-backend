package com.frms.foodrecycling.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShoutOut {

    @Id
    private String id;

    private String foodType;

    private String foodItem;

    private String quantity;

    private LocalDate date;

    private boolean responded;

    @OneToOne
    private Donor donor;
}
