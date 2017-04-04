package com.example.model;

import com.example.utility.LocalDateAdapter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;


/**
 * Created by AjmalCholassery on 3/23/17.
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@DiscriminatorColumn(name = "partytype", discriminatorType = DiscriminatorType.STRING )
abstract class Party {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Long nameid;

    @XmlElement
    private final String clientId;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private final LocalDate effectdt;
    private final Long auditid;
    private final String searchkey;
    private final LocalDate xpirdt;

}
