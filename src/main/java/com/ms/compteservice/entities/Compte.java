package com.ms.compteservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ms.compteservice.model.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity

@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private double solde;

    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TypeCompte type;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EtatCompte etat;

    @OneToMany(mappedBy = "compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Operation> operations;

    private Long clientID;
    @Transient
    private Client client;
}
