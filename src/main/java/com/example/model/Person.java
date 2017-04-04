package com.example.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;


/**
 * Created by AjmalCholassery on 3/23/17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
//@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@DiscriminatorValue(value = "P")
public class Person extends Party {

    public enum SEX{
        MALE,
        FEMALE
    }
//    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long nameid;
    private String firstname;
    private final String lastname;
    private final LocalDate birthdt;
    private final SEX sex;

    public Person(String clientId, LocalDate effectdt, Long auditid, String searchkey,
                  LocalDate xpirdt, String firstname, String lastname, LocalDate birthdt, SEX sex){
        super(clientId, effectdt, auditid, searchkey,xpirdt);
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdt = birthdt;
        this.sex =  sex;
    }


}
