package com.sleepbetter.sleepbetter.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nom;
    private String email;
    private String motDePasse;
    private Integer age;
}
