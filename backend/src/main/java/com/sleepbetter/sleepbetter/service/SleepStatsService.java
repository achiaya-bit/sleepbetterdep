package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.dto.SleepStatsResponse;
import com.sleepbetter.sleepbetter.entity.EntreeSommeil;
import com.sleepbetter.sleepbetter.repository.EntreeSommeilRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SleepStatsService {

    private final EntreeSommeilRepository repository;

    public SleepStatsService(EntreeSommeilRepository repository) {
        this.repository = repository;
    }

    /* ========= MÉTHODE APPELÉE PAR LES CONTROLLERS ========= */
    public SleepStatsResponse getStats(Long idUtilisateur) {

        List<EntreeSommeil> entrees =
                repository.findByUtilisateur_Id(idUtilisateur);

        SleepStatsResponse response = new SleepStatsResponse();
        response.setIdUtilisateur(idUtilisateur);

        if (entrees.isEmpty()) {
            response.setTotalNuits(0);
            return response;
        }

        double moyenneDuree = entrees.stream()
                .mapToInt(e -> Math.max(e.getDuree(), 0)) // protège contre durées négatives
                .average()
                .orElse(0);

        double moyenneFatigue = entrees.stream()
                .mapToInt(EntreeSommeil::getFatigue)
                .average()
                .orElse(0);

        double moyenneQualite = entrees.stream()
                .mapToInt(EntreeSommeil::getQualite)
                .average()
                .orElse(0);

        int nuitsCourtes = (int) entrees.stream()
                .filter(e -> e.getDuree() >= 0 && e.getDuree() < 6)
                .count();

        int nuitsCorrectes = (int) entrees.stream()
                .filter(e -> e.getDuree() >= 6 && e.getDuree() <= 8)
                .count();

        int nuitsLongues = (int) entrees.stream()
                .filter(e -> e.getDuree() > 8)
                .count();

        response.setMoyenneDuree(arrondir(moyenneDuree));
        response.setMoyenneFatigue(arrondir(moyenneFatigue));
        response.setMoyenneQualite(arrondir(moyenneQualite));

        response.setNuitsCourtes(nuitsCourtes);
        response.setNuitsCorrectes(nuitsCorrectes);
        response.setNuitsLongues(nuitsLongues);

        response.setTotalNuits(entrees.size());

        return response;
    }

    private double arrondir(double valeur) {
        return Math.round(valeur * 10.0) / 10.0;
    }


    private double round(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}
