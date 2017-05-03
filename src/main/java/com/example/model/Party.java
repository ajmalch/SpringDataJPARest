package com.example.model;

import com.example.utility.json.LocalDateDeserializer;
import com.example.utility.json.LocalDateSerializer;
import com.example.utility.xml.LocalDateAdapter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @Column(unique = true, nullable = false)
    private final String clientId;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate effectdt;

    private final Long auditid;
    private final String searchkey;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate xpirdt;

}
