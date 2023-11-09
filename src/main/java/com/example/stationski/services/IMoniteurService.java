package com.example.stationski.services;

import com.example.stationski.entities.Moniteur;

import java.util.List;
import java.util.Optional;

public interface IMoniteurService {

    List<Moniteur> retrieveAllMoniteurs();

    Moniteur addMoniteur(Moniteur m);

    Optional<Moniteur> updateMoniteur (Moniteur m, Integer id);

    Optional<Moniteur> retrieveMoniteur (Integer idMoniteur);

    void deleteMoniteur( Integer idMoniteur);

    Moniteur addMoniteurAndAssignToCourse(Moniteur moniteur);

    Moniteur bestMoniteur();



}
