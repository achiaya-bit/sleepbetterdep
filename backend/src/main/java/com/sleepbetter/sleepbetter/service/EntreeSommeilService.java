package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.dto.SleepCreateRequest;
import com.sleepbetter.sleepbetter.dto.SleepResponse;
import com.sleepbetter.sleepbetter.dto.SleepUpdateRequest;
import com.sleepbetter.sleepbetter.entity.EntreeSommeil;
import com.sleepbetter.sleepbetter.entity.Utilisateur;
import com.sleepbetter.sleepbetter.exception.ResourceNotFoundException;
import com.sleepbetter.sleepbetter.repository.EntreeSommeilRepository;
import com.sleepbetter.sleepbetter.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class EntreeSommeilService {

    private final EntreeSommeilRepository entreeSommeilRepository;
    private final UtilisateurRepository utilisateurRepository;

    public EntreeSommeilService(EntreeSommeilRepository entreeSommeilRepository,
                                UtilisateurRepository utilisateurRepository) {
        this.entreeSommeilRepository = entreeSommeilRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /* ===================== CREATE ===================== */

    public SleepResponse create(Long idUtilisateur, SleepCreateRequest request) {

        Utilisateur utilisateur = utilisateurRepository.findById(idUtilisateur)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Utilisateur introuvable"));

        EntreeSommeil entree = new EntreeSommeil();
        entree.setUtilisateur(utilisateur);
        entree.setDateJour(request.getDateJour());
        entree.setHeureCoucher(request.getHeureCoucher());
        entree.setHeureReveil(request.getHeureReveil());
        entree.setFatigue(request.getFatigue());
        entree.setQualite(request.getQualite());
        entree.setHumeur(request.getHumeur());
        entree.setCommentaire(request.getCommentaire());

        int duree = calculerDuree(
                request.getDateJour(),
                request.getHeureCoucher(),
                request.getHeureReveil()
        );
        entree.setDuree(duree);

        return toDto(entreeSommeilRepository.save(entree));
    }

    /* ===================== READ ===================== */

    public List<SleepResponse> getByUtilisateur(Long idUtilisateur) {

        return entreeSommeilRepository.findByUtilisateur_Id(idUtilisateur)
                .stream()
                .map(this::toDto)
                .toList();
    }

    /* ===================== UPDATE ===================== */

    public SleepResponse update(Long idEntree, SleepUpdateRequest request) {

        EntreeSommeil entree = entreeSommeilRepository.findById(idEntree)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Entrée de sommeil introuvable"));

        entree.setDateJour(request.getDateJour());
        entree.setHeureCoucher(request.getHeureCoucher());
        entree.setHeureReveil(request.getHeureReveil());
        entree.setFatigue(request.getFatigue());
        entree.setQualite(request.getQualite());
        entree.setHumeur(request.getHumeur());
        entree.setCommentaire(request.getCommentaire());

        int duree = calculerDuree(
                request.getDateJour(),
                request.getHeureCoucher(),
                request.getHeureReveil()
        );
        entree.setDuree(duree);

        return toDto(entreeSommeilRepository.save(entree));
    }

    /* ===================== DELETE ===================== */

    public void delete(Long idEntree) {
        entreeSommeilRepository.deleteById(idEntree);
    }

    /* ===================== LOGIQUE MÉTIER ===================== */

    private int calculerDuree(LocalDate date,
                              LocalTime heureCoucher,
                              LocalTime heureReveil) {

        LocalDateTime debut = LocalDateTime.of(date, heureCoucher);
        LocalDateTime fin = LocalDateTime.of(date, heureReveil);

        // Si le réveil est le lendemain
        if (heureReveil.isBefore(heureCoucher)) {
            fin = fin.plusDays(1);
        }

        return (int) Duration.between(debut, fin).toHours();
    }

    /* ===================== MAPPING DTO ===================== */

    private SleepResponse toDto(EntreeSommeil entree) {

        SleepResponse dto = new SleepResponse();
        dto.setIdEntree(entree.getIdEntree());
        dto.setDateJour(entree.getDateJour());
        dto.setHeureCoucher(entree.getHeureCoucher());
        dto.setHeureReveil(entree.getHeureReveil());
        dto.setDuree(entree.getDuree());
        dto.setFatigue(entree.getFatigue());
        dto.setQualite(entree.getQualite());
        dto.setHumeur(entree.getHumeur());
        dto.setCommentaire(entree.getCommentaire());
        dto.setIdUtilisateur(entree.getUtilisateur().getId());

        return dto;
    }
}
