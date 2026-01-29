package com.sleepbetter.sleepbetter.controller;

import com.sleepbetter.sleepbetter.entity.Alerte;
import com.sleepbetter.sleepbetter.service.AlerteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alertes")
@CrossOrigin("*")
public class AlerteController {

    private final AlerteService service;

    public AlerteController(AlerteService service) {
        this.service = service;
    }

    @GetMapping("/{idUtilisateur}")
    public List<Alerte> getAlertes(@PathVariable Long idUtilisateur) {
        return service.getAlertesUtilisateur(idUtilisateur);
    }
}
