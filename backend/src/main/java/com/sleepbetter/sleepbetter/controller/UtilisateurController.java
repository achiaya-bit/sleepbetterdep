package com.sleepbetter.sleepbetter.controller;

import com.sleepbetter.sleepbetter.dto.LoginRequest;
import com.sleepbetter.sleepbetter.dto.RegisterRequest;
import com.sleepbetter.sleepbetter.entity.Utilisateur;
import com.sleepbetter.sleepbetter.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UtilisateurController {

    @Autowired
    private UtilisateurService service;

    @PostMapping("/register")
    public Utilisateur register(@RequestBody RegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public Utilisateur login(@RequestBody LoginRequest request) {
        return service.login(request);
    }
}
