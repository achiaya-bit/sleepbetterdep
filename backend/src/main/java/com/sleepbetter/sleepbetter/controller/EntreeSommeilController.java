package com.sleepbetter.sleepbetter.controller;

import com.sleepbetter.sleepbetter.dto.SleepResponse;
import com.sleepbetter.sleepbetter.dto.SleepCreateRequest;
import com.sleepbetter.sleepbetter.dto.SleepUpdateRequest;
import com.sleepbetter.sleepbetter.service.EntreeSommeilService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sleep")
@CrossOrigin(origins = "*")
public class EntreeSommeilController {

    @Autowired
    private EntreeSommeilService service;

    @PostMapping("/{idUtilisateur}")
    public SleepResponse create(@PathVariable Long idUtilisateur,
                                @RequestBody SleepCreateRequest req) {
        return service.create(idUtilisateur, req);
    }

    @GetMapping("/{idUtilisateur}")
    public List<SleepResponse> getByUser(@PathVariable Long idUtilisateur) {
        return service.getByUtilisateur(idUtilisateur);
    }

    @PutMapping("/{idEntree}")
    public SleepResponse update(@PathVariable Long idEntree,
                                @RequestBody SleepUpdateRequest req) {
        return service.update(idEntree, req);
    }

    @DeleteMapping("/{idEntree}")
    public void delete(@PathVariable Long idEntree) {
        service.delete(idEntree);
    }
}
