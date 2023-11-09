package com.example.stationski.services;

import com.example.stationski.entities.Cours;
import com.example.stationski.entities.Moniteur;
import com.example.stationski.repositories.MoniteurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class MoniteurServiceImplMockTest {
    @Mock
    private MoniteurRepository moniteurRepository;

    @InjectMocks
    private MoniteurServiceImpl moniteurService;

    @Test
    public void testRetrieveMoniteur() {
        Moniteur moniteur = Moniteur.builder()
                .numMoniteur(896523L)
                .nomM("Makni")
                .prenomM("Amal")
                .dateRecru(LocalDate.now())
                .prime(500)
                .build();
        when(moniteurRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(moniteur));
        Optional<Moniteur> m = moniteurService.retrieveMoniteur(2);
        assertNotNull(m);
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

        Moniteur bestMoniteur = moniteurService.bestMoniteur();

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


    @Test
    void updateMoniteurTest() {

        Moniteur existingMoniteur = Moniteur.builder().nomM("test").prenomM("test").build();
        when(moniteurRepository.findById(5)).thenReturn(Optional.of(existingMoniteur));

        Moniteur updatedMoniteurData = Moniteur.builder().nomM("update").prenomM("test").build();
        when(moniteurRepository.save(existingMoniteur)).thenReturn(updatedMoniteurData);

        Optional<Moniteur> updatedMoniteur = moniteurService.updateMoniteur(updatedMoniteurData, 5);
        verify(moniteurRepository).findById(5);
        verify(moniteurRepository).save(existingMoniteur);
        assertTrue(updatedMoniteur.isPresent());
        assertEquals("update", updatedMoniteur.get().getNomM());  // Assuming you want to check for "update"
        assertEquals("test", updatedMoniteur.get().getPrenomM()); // Assuming you want to check for "test"
    }


    @Test
    void addEtudiantTest() {
        Moniteur m = Moniteur.builder().nomM("TestNom").prenomM("TestPrenom").build();
        when(moniteurRepository.save(m)).thenReturn(m);
        Moniteur addedMoniteur = moniteurService.addMoniteur(m);
        assertNotNull(addedMoniteur);
        assertEquals("TestNom", addedMoniteur.getNomM());
        assertEquals("TestPrenom", addedMoniteur.getPrenomM());
    }

}
