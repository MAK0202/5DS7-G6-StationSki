package com.example.stationski.dto;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Skieur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idInscription")
    private Integer idInscription; // Clé primaire
    private Long numInscription;
    private Integer numSemaine;
    @ManyToOne
    private Skieur skieur;
    @ManyToOne()
    @JsonIgnore
    private Cours cours;
}
