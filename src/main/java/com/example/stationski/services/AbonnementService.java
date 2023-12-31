package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.AbonnementRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class AbonnementService implements IAbonnementService{

    AbonnementRepository abonnementRepository;
    SkieurRepository skieurRepository;
    @Override
    public Set<Abonnement> getAbonnementByType(TypeAbonnement type) {
        return abonnementRepository.findByTypeAbon(type);
    }

    @Override
    public List<Abonnement> retrieveAbonnementByDates(LocalDate startDate, LocalDate endDate) {
        return abonnementRepository.getAbonnementsByDateDebutBetween(startDate, endDate);

    }
}
