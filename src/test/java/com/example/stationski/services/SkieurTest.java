package com.example.stationski.services;

import com.example.stationski.entities.Abonnement;
import com.example.stationski.entities.Skieur;
import com.example.stationski.entities.TypeAbonnement;
import com.example.stationski.repositories.SkieurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SkieurTest {


    @Autowired
    SkieurRepository SR;
    @Autowired
    SkieurService SS;

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
        List<Skieur> LSsaved = SR.findByAbonnementTypeAbon(TypeAbonnement.ANNUEL);
        assertNotNull(LSsaved);
    }
    @Test
    public void testAddSkieur() {
        // Create a Skieur object
        Skieur skieur = new Skieur();
        skieur.setNomS("John");
        skieur.setPrenomS("Doe");

        // Call the method to be tested
        Skieur Sresult = SR.save(skieur);
        assertNotNull(Sresult);

        // Retrieve the Skieur from the database and verify its properties
        Skieur savedSkieur = SR.findById(Sresult.getIdSkieur()).orElse(null);
        assertNotNull(savedSkieur);
        assertEquals("John", savedSkieur.getNomS());
        assertEquals("Doe", savedSkieur.getPrenomS());
    }


    @Test
    public void testRemoveSkieur() {
        // Call the method to be tested
        Skieur S = new Skieur();
        S.setIdSkieur(1);
        Skieur savedSkieur = SR.save(S);

        assertNotNull(savedSkieur);
        assertTrue(SR.existsById(savedSkieur.getIdSkieur()));

        // No delay needed, rely on proper synchronization or database transactions

        SS.removeSkieur(savedSkieur.getIdSkieur());

        // Verify that the Skieur with ID 1 is no longer in the database
        assertFalse(SR.existsById(savedSkieur.getIdSkieur()));
    }


}
