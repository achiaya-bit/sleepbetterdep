package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.entity.Alerte;
import com.sleepbetter.sleepbetter.entity.Utilisateur;
import com.sleepbetter.sleepbetter.repository.AlerteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlerteService {

    private final AlerteRepository repo;

    public AlerteService(AlerteRepository repo) {
        this.repo = repo;
    }

    public void creerAlerteNuitsCourtes(Utilisateur utilisateur, int nuitsCourtes) {

        if (nuitsCourtes >= 3) {

            Alerte alerte = new Alerte();
            alerte.setUtilisateur(utilisateur);
            alerte.setDateCreation(LocalDateTime.now());
            alerte.setMessage(
                    "Attention : vous avez dormi moins de 6h pendant plusieurs nuits."
            );

            repo.save(alerte);
        }
    }

    public List<Alerte> getAlertesUtilisateur(Long idUtilisateur) {
        return repo.findByUtilisateur_Id(idUtilisateur);
    }
}
