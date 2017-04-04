package com.example.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
public class Organization extends Party{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nameid;
    private String shortname;
    @XmlElement
    private final String orgname;

    public Organization(String clientId, LocalDate effectdt, Long auditid, String searchkey,
                        LocalDate xpirdt, String shortname, String orgname){
        super(clientId, effectdt, auditid, searchkey,xpirdt);
        this.shortname = shortname;
        this.orgname = orgname;

    }

}
