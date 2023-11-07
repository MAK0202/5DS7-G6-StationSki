package com.example.stationski.services;
import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.MoniteurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j


public class MoniteurServiceImplTest {
    @Mock
    private MoniteurRepository moniteurRepository;

    @InjectMocks
    private MoniteurServiceImpl moniteurservice;
    @Autowired
    private MoniteurServiceImpl moniteurService;

    @AfterEach
    public void clearDatabase() {
        // Delete all Moniteur records from the database
        moniteurRepository.deleteAll();
    }



    @Test
    public void testAddMoniteur() throws ParseException{
        LocalDate date = LocalDate.of(2023, 2, 13);
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Makni")
                .prenomM("Amal")
                .dateRecru(date)
                .prime(500)
                .build();

        Moniteur savedmoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(savedmoniteur);

        assertEquals(savedmoniteur,moniteur);
        // Delete the Moniteur
        //moniteurService.deleteMoniteur(moniteur.getIdMoniteur());
        // Verify that the Moniteur no longer exists
        //assertNull(moniteurService.retrieveMoniteur(savedmoniteur.getIdMoniteur()));
    }

    @Test
    public void testRetrieveMoniteur() {
        // Create a Moniteur
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Slama")
                .prenomM("Zeineb")
                .dateRecru(LocalDate.of(2023, 2, 13))
                .prime(500)
                .build();

        // Mock the behavior of moniteurRepository
        when(moniteurRepository.save(moniteur)).thenReturn(moniteur);

        // Add the Moniteur
        Moniteur savedMoniteur = moniteurService.addMoniteur(moniteur);
        assertNotNull(savedMoniteur);

        // Mock the behavior of moniteurRepository to return the savedMoniteur
        when(moniteurRepository.findById(savedMoniteur.getIdMoniteur())).thenReturn(Optional.of(savedMoniteur));

        // Retrieve the Moniteur
        Moniteur retrievedMoniteur = moniteurService.retrieveMoniteur(savedMoniteur.getIdMoniteur());
        assertNotNull(retrievedMoniteur);

        // Verify that the retrieved Moniteur matches the original one
        assertEquals(savedMoniteur, retrievedMoniteur);

        // Delete the Moniteur
        //moniteurService.deleteMoniteur(savedMoniteur.getIdMoniteur());

        // Verify that the Moniteur no longer exists
        //assertNull(moniteurService.retrieveMoniteur(savedMoniteur.getIdMoniteur()));
    }

    @Test
    public void testDeleteMoniteur() throws ParseException {
        int nb = moniteurService.retrieveAllMoniteurs().size();
        // Create a Moniteur and add it to the database
        LocalDate date = LocalDate.of(2023, 2, 13);
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Sboui")
                .prenomM("Slimen")
                .dateRecru(date)
                .prime(500)
                .build();

        Moniteur savedMoniteur = moniteurService.addMoniteur(moniteur);

        // Delete the Moniteur
        moniteurService.deleteMoniteur(savedMoniteur.getIdMoniteur());

        // Verify that the Moniteur no longer exists
        assertNull(moniteurService.retrieveMoniteur(savedMoniteur.getIdMoniteur()));
        int nbtest = moniteurService.retrieveAllMoniteurs().size();
        assertEquals(nb,nbtest);
    }



    @Test
    public void testUpdateMoniteur() throws ParseException {
        // Create a Moniteur and add it to the database
        LocalDate date = LocalDate.of(2023, 6, 13);
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("laabidi")
                .prenomM("fadhila")
                .dateRecru(date)
                .prime(900)
                .build();

        Moniteur savedMoniteur = moniteurService.addMoniteur(moniteur);

        // Modify the Moniteur's attributes
        savedMoniteur.setNomM("Lasmer");
        savedMoniteur.setPrime(1500);

        // Update the Moniteur in the database
        Moniteur updatedMoniteur = moniteurService.updateMoniteur(savedMoniteur);

        // Retrieve the updated Moniteur
        Moniteur retrievedMoniteur = moniteurService.retrieveMoniteur(savedMoniteur.getIdMoniteur());

        // Verify that the retrieved Moniteur matches the updated one
        assertNotNull(retrievedMoniteur);
        assertEquals(updatedMoniteur, retrievedMoniteur);
        // Verify that the name and prime are updated
        assertNotEquals(updatedMoniteur.getNomM(),savedMoniteur.getNomM());
        assertNotEquals(updatedMoniteur.getPrime(),savedMoniteur.getPrime());

        // Clean up by deleting the Moniteur
        //moniteurService.deleteMoniteur(savedMoniteur.getIdMoniteur());
    }

    @Test
    public void testBestMoniteur() {
        // Create some sample Moniteurs
        Moniteur moniteur1 = createMoniteurWithCourses(3);
        Moniteur moniteur2 = createMoniteurWithCourses(5);
        Moniteur moniteur3 = createMoniteurWithCourses(2);

        moniteurService.addMoniteur(moniteur1);
        moniteurService.addMoniteur(moniteur2);
        moniteurService.addMoniteur(moniteur3);

        when(moniteurRepository.findAll()).thenReturn(Arrays.asList(moniteur1, moniteur2, moniteur3));

        Moniteur bestMoniteur = moniteurservice.bestMoniteur();

        // Verify that the bestMoniteur has the maximum number of courses
        assertEquals(5, bestMoniteur.getCoursSet().size());

        // Verify that the bestMoniteur's prime has been updated
        assertEquals(10000, bestMoniteur.getPrime());
    }
    private Moniteur createMoniteurWithCourses(int numCourses) {
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Makni")
                .prenomM("Amal")
                .dateRecru(LocalDate.now())
                .prime(500)
                .build();

        Set<Cours> courses = new HashSet<>();
        for (int i = 0; i < numCourses; i++) {
            Cours course = Cours.builder()
                    .nomCours("Course " + i)
                    .build();
            courses.add(course);
        }
        moniteur.setCoursSet(courses);

        return moniteur;
    }

    @Test
    public void testRetrieveAllMoniteurs() {
        // Create some sample Moniteurs
        Moniteur moniteur1 = Moniteur.builder()
                .numMoniteur(253698L)
                .nomM("Ben Marzouk")
                .prenomM("Sinda")
                .dateRecru(LocalDate.now())
                .prime(25000)
                .build();
        Moniteur moniteur2 = Moniteur.builder()
                .numMoniteur(147412L)
                .nomM("Chebbi")
                .prenomM("Mariem")
                .dateRecru(LocalDate.now())
                .prime(58630)
                .build();

        Moniteur moniteur3 = Moniteur.builder()
                .numMoniteur(963853L)
                .nomM("Alwaoui")
                .prenomM("Mayssa")
                .dateRecru(LocalDate.now())
                .prime(36255)
                .build();

        moniteurService.addMoniteur(moniteur1);
        moniteurService.addMoniteur(moniteur2);
        moniteurService.addMoniteur(moniteur3);

        // Mock the behavior of moniteurRepository
        when(moniteurRepository.findAll()).thenReturn(Arrays.asList(moniteur1, moniteur2, moniteur3));

        // Call the retrieveAllMoniteurs method
        List<Moniteur> allMoniteurs = moniteurService.retrieveAllMoniteurs();

        // Verify that the list of Moniteurs matches the expected result
        assertEquals(3, allMoniteurs.size());
        assertEquals("Ben Marzouk", allMoniteurs.get(0).getNomM());
        assertEquals("Chebbi", allMoniteurs.get(1).getNomM());
        assertEquals("Alwaoui", allMoniteurs.get(2).getNomM());
    }






























}

