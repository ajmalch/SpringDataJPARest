package com.example.model;


import com.example.utility.json.LocalDateDeserializer;
import com.example.utility.json.LocalDateSerializer;
import com.example.utility.xml.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Person extends Party {

    public enum SEX{
        MALE,
        FEMALE
    }
//    @Id
//    @GeneratedValue( strategy = GenerationType.AUTO)
//    private Long nameId;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private final String lastName;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @Column(name = "dateOfBirth")
    private final LocalDate dateOfBirth;
    private final SEX sex;

    @Builder
    private Person(String clientId, LocalDate effectiveDate, Long auditid, String searchkey,
                   LocalDate expiryDate, String firstName, String lastName, LocalDate dateOfBirth, SEX sex){
        super(clientId, effectiveDate, auditid, searchkey,expiryDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex =  sex;
    }


}
