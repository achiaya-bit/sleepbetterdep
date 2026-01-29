package com.sleepbetter.sleepbetter.dto;

import lombok.Data;

@Data
public class SleepAdviceResponse {

    private Long idUtilisateur;
    private String niveau;      // INFO | WARNING | CRITICAL
    private String message;
}
