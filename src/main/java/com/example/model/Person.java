package com.example.model;


import com.example.utility.json.LocalDateDeserializer;
import com.example.utility.json.LocalDateSerializer;
import com.example.utility.xml.LocalDateAdapter;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Set;


/**
 * Created by AjmalCholassery on 3/23/17.
 */
@Data
//@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Entity
@DiscriminatorValue(value = "P")
@JsonInclude(JsonInclude.Include.NON_NULL)
@EntityListeners(AuditingEntityListener.class)
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
    @ApiModelProperty(example ="2018-01-01")
    private final  LocalDate dateOfBirth;
    private final SEX sex;

    @LastModifiedDate
    @ApiModelProperty(example ="2018-01-01")
    private LocalDate lastModifiedDate;


    @Builder
    private Person(String clientId, LocalDate effectiveDate, Long auditid,
                   String searchkey,LocalDate expiryDate, String firstName,
                   String lastName, LocalDate dateOfBirth, SEX sex){
        super(clientId, effectiveDate, auditid, searchkey,expiryDate);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.sex =  sex;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Address> addresses;

}
