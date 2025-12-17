package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.dto.SleepCreateRequest;
import com.sleepbetter.sleepbetter.dto.SleepUpdateRequest;
import com.sleepbetter.sleepbetter.dto.SleepResponse;
import com.sleepbetter.sleepbetter.entity.EntreeSommeil;
import com.sleepbetter.sleepbetter.entity.Utilisateur;
import com.sleepbetter.sleepbetter.repository.EntreeSommeilRepository;
import com.sleepbetter.sleepbetter.repository.UtilisateurRepository;
import com.sleepbetter.sleepbetter.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;


@Service
public class EntreeSommeilService {

    @Autowired
    private EntreeSommeilRepository repo;

    @Autowired
    private UtilisateurRepository utilisateurRepo;

    public SleepResponse create(Long idUtilisateur, SleepCreateRequest req) {
        Utilisateur utilisateur = utilisateurRepo.findById(idUtilisateur)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        EntreeSommeil e = new EntreeSommeil();
        e.setUtilisateur(utilisateur);
        e.setDateJour(req.getDateJour());
        e.setHeureCoucher(req.getHeureCoucher());
        e.setHeureReveil(req.getHeureReveil());
        e.setFatigue(req.getFatigue());
        e.setQualite(req.getQualite());
        e.setHumeur(req.getHumeur());
        e.setCommentaire(req.getCommentaire());

        int duree = (int) Duration.between(req.getHeureCoucher(), req.getHeureReveil()).toHours();
        e.setDuree(duree);

        return toDto(repo.save(e));
    }

    public List<SleepResponse> getByUtilisateur(Long idUtilisateur) {
        return repo.findByUtilisateurId(idUtilisateur)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public SleepResponse update(Long idEntree, SleepUpdateRequest req) {
        EntreeSommeil e = repo.findById(idEntree)
                .orElseThrow(() -> new ResourceNotFoundException("Entrée de sommeil introuvable"));

        e.setDateJour(req.getDateJour());
        e.setHeureCoucher(req.getHeureCoucher());
        e.setHeureReveil(req.getHeureReveil());
        e.setFatigue(req.getFatigue());
        e.setQualite(req.getQualite());
        e.setHumeur(req.getHumeur());
        e.setCommentaire(req.getCommentaire());

        int duree = (int) Duration.between(req.getHeureCoucher(), req.getHeureReveil()).toHours();
        e.setDuree(duree);

        return toDto(repo.save(e));
    }

    public void delete(Long idEntree) {
        repo.deleteById(idEntree);
    }

    private SleepResponse toDto(EntreeSommeil e) {
        SleepResponse dto = new SleepResponse();
        dto.setIdEntree(e.getIdEntree());
        dto.setDateJour(e.getDateJour());
        dto.setHeureCoucher(e.getHeureCoucher());
        dto.setHeureReveil(e.getHeureReveil());
        dto.setDuree(e.getDuree());
        dto.setFatigue(e.getFatigue());
        dto.setQualite(e.getQualite());
        dto.setHumeur(e.getHumeur());
        dto.setCommentaire(e.getCommentaire());
        dto.setIdUtilisateur(e.getUtilisateur().getId());
        return dto;
    }
}
