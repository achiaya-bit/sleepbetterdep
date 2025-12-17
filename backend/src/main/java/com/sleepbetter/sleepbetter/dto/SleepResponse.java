package com.sleepbetter.sleepbetter.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SleepResponse {

    private Long idEntree;
    private LocalDate dateJour;
    private LocalTime heureCoucher;
    private LocalTime heureReveil;

    private Integer duree;
    private Integer fatigue;
    private Integer qualite;
    private String humeur;
    private String commentaire;

    private Long idUtilisateur;
}
