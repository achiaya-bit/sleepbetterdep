package com.sleepbetter.sleepbetter.service;

import com.sleepbetter.sleepbetter.dto.SleepAdviceResponse;
import com.sleepbetter.sleepbetter.dto.SleepStatsResponse;
import org.springframework.stereotype.Service;

@Service
public class AdviceService {

    public SleepAdviceResponse generateAdvice(Long idUtilisateur, SleepStatsResponse stats) {

        SleepAdviceResponse response = new SleepAdviceResponse();
        response.setIdUtilisateur(idUtilisateur);

        if (stats.getMoyenneDuree() < 5) {
            response.setNiveau("CRITICAL");
            response.setMessage("Votre durée moyenne de sommeil est trop faible. Essayez de dormir au moins 7h par nuit.");
        }
        else if (stats.getMoyenneQualite() < 5) {
            response.setNiveau("WARNING");
            response.setMessage("La qualité de votre sommeil est moyenne. Réduisez les écrans avant le coucher.");
        }
        else {
            response.setNiveau("INFO");
            response.setMessage("Votre sommeil est globalement bon. Continuez ainsi !");
        }

        return response;
    }
}
