package com.example.model;


import com.example.utility.json.LocalDateDeserializer;
import com.example.utility.json.LocalDateSerializer;
import com.example.utility.xml.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
//    private Long nameid;
    private String firstname;
    private final String lastname;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
