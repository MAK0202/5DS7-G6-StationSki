package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.CoursRepository;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class SkieurServiceTest {

    @Mock
    private SkieurRepository SR;

    @Mock
    private CoursRepository coursRepository;

    @InjectMocks
    private SkieurService SSM;




//    @Test
//    public void testAssignSkieurToPiste() {
//        Long numSkieur = 123L;
//        Long numPiste = 444L;
//        Piste p = new Piste();
//        p.setNumPiste(numPiste);
//        LocalDate date = LocalDate.of(1999, 2, 13);
//        Skieur SK1 = Skieur.builder()
//                .numSkieur(numSkieur)
//                .nomS("Kass")
//                .prenomS("Aziz")
//                .dateNaissance(date)
//                .ville("Manouba")
//                .build();
//        Skieur SavedSkieur = SS.assignSkieurToPiste(numSkieur,numPiste);
//        assertNotNull(SavedSkieur);
//        assertEquals(SavedSkieur,SK1);
//        when(SSM.assignSkieurToPiste(numSkieur,numPiste)).thenReturn(SK1);
//
//    }
//    @Test
//    public void testAddSkieurAndAssignToCourse() {
//        // Create a mock Skieur object
//        Set<Inscription> li = new HashSet<Inscription>();
//
//        Skieur skieur = Skieur.builder().numSkieur(14354580L).nomS("Kass").prenomS("Aziz").ville("Manouba").inscriptions(li).build();
//
//        // Create a mock Cours object
//        Cours cours = new Cours();
//        cours.setNumCours(1L);
//
//    // Mock the behavior of coursRepository.findByNumCours(numCourse)
//        when(coursRepository.findByNumCours(1L)).thenReturn(cours);
//
//        // Mock the behavior of skieurRepository.save(skieur)
//        when(SR.save(skieur)).thenReturn(skieur);
//
//        // Call the method to be tested
//        Skieur result = SSM.addSkieurAndAssignToCourse(skieur, 1L);
//
//        assertNotNull(result);
//        assertEquals(skieur.getNumSkieur(), result.getNumSkieur());
//        assertEquals(skieur.getNomS(), result.getNomS());
//        assertEquals(skieur.getPrenomS(), result.getPrenomS());
//        assertEquals(skieur.getVille(), result.getVille());
//
//    }



//    @Test
//    public void testNombreSkieursParCouleurPiste() {
//
//
//        Piste p1 = Piste.builder().nomPiste("RP").numPiste(123L).couleur(Couleur.ROUGE).build();
//        Piste p2 = Piste.builder().nomPiste("RPNew").numPiste(124L).couleur(Couleur.ROUGE).build();
//        Piste p3 = Piste.builder().nomPiste("RPLarge").numPiste(125L).couleur(Couleur.ROUGE).build();
//
//        Piste p4 = Piste.builder().nomPiste("BB").numPiste(128L).couleur(Couleur.BLEU).build();
//        Piste p5 = Piste.builder().nomPiste("BBL").numPiste(129L).couleur(Couleur.BLEU).build();
//
//        Piste p6 = Piste.builder().nomPiste("Noir").numPiste(155L).couleur(Couleur.NOIR).build();
//
//        Set<Piste> ListPistes = null ;
//        ListPistes.add(p1) ;
//        ListPistes.add(p2) ;
//        ListPistes.add(p3) ;
//        ListPistes.add(p4) ;
//        ListPistes.add(p5) ;
//        ListPistes.add(p6) ;
//
//        EnumMap<Couleur, Integer> CM = null;
//        CM.put(Couleur.ROUGE,3);
//        CM.put(Couleur.BLEU,2);
//        CM.put(Couleur.NOIR,1);
//        CM.put(Couleur.VERT,0);
//        LocalDate date = LocalDate.of(1999, 2, 13);
//        Skieur SK1 = Skieur.builder()
//                .numSkieur(14354580L)
//                .nomS("Kass")
//                .prenomS("Aziz")
//                .dateNaissance(date)
//                .ville("Manouba")
//                .build();
//        SK1.setPistes(ListPistes);
//
//        EnumMap<Couleur, Integer> Result = SS.nombreSkieursParCouleurPiste();
//        assertNotNull(Result);
//        assertEquals(Result,CM);
//    }
//
//     @Test
//     public void testAddSkieurAndAssignToCourse2() {
//         Skieur SK1 = Skieur.builder()
//                 .idSkieur(1)
//                 .numSkieur(14354580L)
//                 .nomS("Kass")
//                 .prenomS("Aziz")
//                 .ville("Manouba")
//                 .build();
//         SR.save(SK1);
//
//         Cours C = new Cours();
//         C.setIdCours(1);
//         C.setNumCours(1L);
//         Skieur savedS = SS.addSkieurAndAssignToCourse(SK1,1L);
//         assertNotNull(savedS);
//         assertEquals(savedS,SK1);
//         List<Cours> ListC = new ArrayList<>();
//         Set<Inscription> inscriptions;
//         inscriptions= SK1.getInscriptions();
//         inscriptions.stream().forEach(
//                 inscription -> ListC.add(inscription.getCours())
//         );
//         assertEquals(1,ListC.contains(C))  ;
//
//     }
//
//

    @Test
    void testNombreSkieursParCouleurPiste() {
        // Initialize the set before adding elements
        Set<Piste> ListPistes = new HashSet<>();

        Piste p1 = Piste.builder().nomPiste("RP").numPiste(123L).couleur(Couleur.ROUGE).build();
        Piste p2 = Piste.builder().nomPiste("RPNew").numPiste(124L).couleur(Couleur.ROUGE).build();
        Piste p3 = Piste.builder().nomPiste("RPLarge").numPiste(125L).couleur(Couleur.ROUGE).build();
        Piste p4 = Piste.builder().nomPiste("BB").numPiste(128L).couleur(Couleur.BLEU).build();
        Piste p5 = Piste.builder().nomPiste("BBL").numPiste(129L).couleur(Couleur.BLEU).build();
        Piste p6 = Piste.builder().nomPiste("Noir").numPiste(155L).couleur(Couleur.NOIR).build();

        // Add pistes to the set
        ListPistes.add(p1);
        ListPistes.add(p2);
        ListPistes.add(p3);
        ListPistes.add(p4);
        ListPistes.add(p5);
        ListPistes.add(p6);

        // Create mock Skieurs with different colors
        Skieur skieur1 = new Skieur();
        skieur1.setPistes(ListPistes);

        Skieur skieur2 = new Skieur();
        skieur2.setPistes(ListPistes);

        Skieur skieur3 = new Skieur();
        skieur3.setPistes(ListPistes);

        // Mock the behavior of skieurRepository.skieursByCouleurPiste
        when(SR.skieursByCouleurPiste(Couleur.BLEU)).thenReturn(List.of(skieur1, skieur3));
        when(SR.skieursByCouleurPiste(Couleur.VERT)).thenReturn(List.of(skieur2));

        // Call the method to be tested
        EnumMap<Couleur, Integer> result = SSM.nombreSkieursParCouleurPiste();

        // Verify the result
        assertNotNull(result);
        assertEquals(2, result.get(Couleur.BLEU)); // Expecting 2 Skieurs with BLEU color
        assertEquals(1, result.get(Couleur.VERT)); // Expecting 1 Skieur with VERT color
    }

    @Test
    void testAddSkieur() {
        // Create a Skieur object
        Skieur skieur = new Skieur();
        skieur.setNomS("John");
        skieur.setPrenomS("Doe");

        // Mock the behavior of skieurRepository.save
        when(SR.save(skieur)).thenReturn(skieur);

        // Call the method to be tested
        Skieur result = SSM.addSkieur(skieur);

        // Verify the result
        assertNotNull(result);
        assertEquals("John", result.getNomS());
        assertEquals("Doe", result.getPrenomS());

        // Verify that skieurRepository.save was called once with the expected parameter
        verify(SR, times(1)).save(skieur);
    }

    @Test
    void testRemoveSkieur() {
        // Create a Skieur object
        Skieur skieur = new Skieur();
        skieur.setIdSkieur(1);

        // Mock the behavior of skieurRepository.findById
        when(SR.findById(1)).thenReturn(Optional.of(skieur));

        // Call the method to be tested
        SSM.removeSkieur(1);

        // Verify that skieurRepository.findById was called once with the expected parameter
        verify(SR, times(1)).findById(1);

        // Verify that skieurRepository.delete was called once with the expected parameter
        verify(SR, times(1)).delete(skieur);
    }
}
