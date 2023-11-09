package com.example.stationski.services;
import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.MoniteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j


public class MoniteurServiceImplTest {
    @Autowired
    private MoniteurServiceImpl moniteurservice;
    @Autowired
    private MoniteurRepository moniteurRepository;

    @AfterEach
    public void clearDatabase() {
        // Delete all Moniteur records from the database
        moniteurRepository.deleteAll();
    }



    /*@Test
    public void testAddMoniteur() throws ParseException{
        LocalDate date = LocalDate.of(2023, 2, 13);
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Makni")
                .prenomM("Amal")
                .dateRecru(date)
                .prime(500)
                .build();

        Moniteur savedmoniteur = moniteurservice.addMoniteur(moniteur);
        assertNotNull(savedmoniteur);

        assertEquals(savedmoniteur,moniteur);
        // Delete the Moniteur
        //moniteurService.deleteMoniteur(moniteur.getIdMoniteur());
        // Verify that the Moniteur no longer exists
        //assertNull(moniteurService.retrieveMoniteur(savedmoniteur.getIdMoniteur()));
    }*/




   /* public void testUpdateMoniteur() throws ParseException {
        // Create a Moniteur and add it to the database
        LocalDate date = LocalDate.of(2023, 6, 13);
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("laabidi")
                .prenomM("fadhila")
                .dateRecru(date)
                .prime(900)
                .build();

        Moniteur savedMoniteur = moniteurservice.addMoniteur(moniteur);

        // Modify the Moniteur's attributes
        savedMoniteur.setNomM("Lasmer");

        // Update the Moniteur in the database
        Optional<Moniteur> updatedMoniteur = moniteurservice.updateMoniteur(savedMoniteur);

        // Retrieve the updated Moniteur
        Moniteur retrievedMoniteur = moniteurservice.retrieveMoniteur(savedMoniteur.getIdMoniteur());

        // Verify that the retrieved Moniteur matches the updated one
        assertNotNull(retrievedMoniteur);
        assertEquals(updatedMoniteur, retrievedMoniteur);
        // Verify that the name and prime are updated
        //assertNotEquals(updatedMoniteur.getNomM(),savedMoniteur.getNomM());

        // Clean up by deleting the Moniteur
        //moniteurService.deleteMoniteur(savedMoniteur.getIdMoniteur());
    }*/

































}

