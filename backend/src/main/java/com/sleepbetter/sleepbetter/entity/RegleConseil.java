package com.sleepbetter.sleepbetter.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class RegleConseil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegle;

    @Column(name = "rule_condition")
    private String condition;
    private Integer priorite;
    private String message;
    private boolean active;
}
