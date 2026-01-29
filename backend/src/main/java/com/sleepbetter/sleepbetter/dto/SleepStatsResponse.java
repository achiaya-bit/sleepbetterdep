package com.sleepbetter.sleepbetter.dto;

import lombok.Data;

@Data
public class SleepStatsResponse {

    private Long idUtilisateur;

    private double moyenneDuree;
    private double moyenneFatigue;
    private double moyenneQualite;

    private int nuitsCourtes;     // < 6h
    private int nuitsCorrectes;   // 6h–8h
    private int nuitsLongues;     // > 8h

    private int totalNuits;
}
