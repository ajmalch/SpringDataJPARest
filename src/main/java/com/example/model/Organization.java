package com.example.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

/**
 * Created by AjmalCholassery on 3/24/17.
 */
@EqualsAndHashCode(callSuper = true)
@XmlRootElement
@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@DiscriminatorValue(value = "O")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Organization extends Party{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long nameId;

    @Column(name = "shortname")
    private String shortName;

    @XmlElement
    @Column(name = "orgname")
    private final String name;

    @Builder
    public Organization(String clientId, LocalDate effectiveDate, Long auditId, String searchkey,
                        LocalDate expiryDate, String shortName, String name){
        super(clientId, effectiveDate, auditId, searchkey,expiryDate);
        this.shortName = shortName;
        this.name = name;

    }

}
