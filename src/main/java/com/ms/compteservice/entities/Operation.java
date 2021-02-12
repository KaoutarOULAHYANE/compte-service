package com.ms.compteservice.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity

@Data @NoArgsConstructor @AllArgsConstructor  @ToString
public class Operation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;

    @Temporal(TemporalType.DATE)
    private Date date;

    private double montant;

    @ManyToOne
    private Compte compte;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private TypeOperation type;
}
