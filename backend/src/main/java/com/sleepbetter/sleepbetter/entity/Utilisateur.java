package com.sleepbetter.sleepbetter.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @Column(unique = true)
    private String email;

    private String motDePasse;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<EntreeSommeil> entrees;

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<Alerte> alertes;

    public enum Role {
        ADMIN,
        ETUDIANT
    }
}
