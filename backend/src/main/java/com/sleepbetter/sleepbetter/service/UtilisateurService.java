package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.dto.LoginRequest;
import com.sleepbetter.sleepbetter.dto.RegisterRequest;
import com.sleepbetter.sleepbetter.entity.Utilisateur;
import com.sleepbetter.sleepbetter.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur register(RegisterRequest request) {

        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email déjà utilisé !");
        }

        Utilisateur u = new Utilisateur();
        u.setNom(request.getNom());
        u.setEmail(request.getEmail());
        u.setMotDePasse(request.getMotDePasse()); // plus tard on mettra un hash
        u.setAge(request.getAge());
        u.setRole(Utilisateur.Role.ETUDIANT);

        return utilisateurRepository.save(u);
    }

    public Utilisateur login(LoginRequest request) {

        Utilisateur u = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        if (!u.getMotDePasse().equals(request.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect !");
        }

        return u;
    }
}
