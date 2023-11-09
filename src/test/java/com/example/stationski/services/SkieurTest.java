package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SkieurTest {

    @Autowired
    SkieurRepository skieurRepo;

    @Test
    void testRetrieveSkieursByTypeAbonnement() {
        Abonnement AB = new Abonnement();
        AB.setTypeAbon(TypeAbonnement.ANNUEL);
        LocalDate date = LocalDate.of(2000, 2, 13);
        Skieur SK1 = Skieur.builder()
                .numSkieur(896523L)
                .nomS("Makni")
                .prenomS("Amal")
                .dateNaissance(date)
                .ville("Tunis")
                .abonnement(AB)
                .build();
        Skieur SK2 = Skieur.builder()
                .numSkieur(896523L)
                .nomS("Msekni")
                .prenomS("youssef")
                .dateNaissance(date)
                .ville("Bardo")
                .abonnement(AB)
                .build();
        List<Skieur> LSsaved = skieurRepo.findByAbonnementTypeAbon(TypeAbonnement.ANNUEL);
        assertNotNull(LSsaved);
    }
}
