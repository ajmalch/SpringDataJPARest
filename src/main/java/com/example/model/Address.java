package com.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ADDR_KEY")
    private Long addressKey;

    @Column(name = "ADDRID", unique = true)
    private String AddressId;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String country;


    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "NAMEID")
    private Person person;


}
