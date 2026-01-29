package com.sleepbetter.sleepbetter.repository;

import com.sleepbetter.sleepbetter.entity.EntreeSommeil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntreeSommeilRepository
        extends JpaRepository<EntreeSommeil, Long> {

    List<EntreeSommeil> findByUtilisateur_Id(Long idUtilisateur);
}
