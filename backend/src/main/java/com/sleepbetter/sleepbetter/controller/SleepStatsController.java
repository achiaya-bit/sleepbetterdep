package com.sleepbetter.sleepbetter.controller;

import com.sleepbetter.sleepbetter.dto.SleepStatsResponse;
import com.sleepbetter.sleepbetter.service.SleepStatsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")

public class SleepStatsController {

    private final SleepStatsService service;

    public SleepStatsController(SleepStatsService service) {
        this.service = service;
    }

    @GetMapping("/{idUtilisateur}")
    public SleepStatsResponse getStats(@PathVariable Long idUtilisateur) {
        return service.getStats(idUtilisateur);
    }
}
