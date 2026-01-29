package com.sleepbetter.sleepbetter.repository;

import com.sleepbetter.sleepbetter.entity.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlerteRepository extends JpaRepository<Alerte, Long> {

    List<Alerte> findByUtilisateur_Id(Long idUtilisateur);
}
