package com.sleepbetter.sleepbetter.controller;

import com.sleepbetter.sleepbetter.dto.SleepAdviceResponse;
import com.sleepbetter.sleepbetter.dto.SleepStatsResponse;
import com.sleepbetter.sleepbetter.service.AdviceService;
import com.sleepbetter.sleepbetter.service.SleepStatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/advice")
@CrossOrigin("*")
public class AdviceController {

    private final AdviceService adviceService;
    private final SleepStatsService statsService;

    public AdviceController(AdviceService adviceService,
                            SleepStatsService statsService) {
        this.adviceService = adviceService;
        this.statsService = statsService;
    }

    @GetMapping("/{idUtilisateur}")
    public SleepAdviceResponse getAdvice(@PathVariable Long idUtilisateur) {

        SleepStatsResponse stats = statsService.getStats(idUtilisateur);
        return adviceService.generateAdvice(idUtilisateur, stats);
    }
}
