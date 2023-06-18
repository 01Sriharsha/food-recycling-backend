package com.frms.foodrecycling.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true , nullable = false)
    private String email;

    private String name;

    private String phone;

    private String address;

    private String city;

    private String password;

    private String user;

    @OneToMany(mappedBy = "member",cascade = CascadeType.DETACH,fetch = FetchType.LAZY , orphanRemoval = true)
    @JsonIgnore
    private List<RequestFood> requestFoods = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Enquiry> enquiries = new ArrayList<>();
}
