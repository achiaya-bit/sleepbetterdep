package com.sleepbetter.sleepbetter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class EntreeSommeil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntree;

    private LocalDate dateJour;
    private LocalTime heureCoucher;
    private LocalTime heureReveil;

    private Integer duree;
    private Integer fatigue;
    private Integer humeur;
    private String commentaire;
    private Integer qualite;

    @ManyToOne
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;
}
