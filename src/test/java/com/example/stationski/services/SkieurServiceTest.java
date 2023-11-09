package com.example.stationski.services;

import com.example.stationski.entities.*;
import com.example.stationski.repositories.MoniteurRepository;
import com.example.stationski.repositories.SkieurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class SkieurServiceTest {

    @Mock
    private SkieurRepository SR;

    @InjectMocks
    private SkieurService SSM;
    @Autowired
    private SkieurService SS;

    @AfterEach
    public void clearDatabase() {
        // Delete all Moniteur records from the database
        SR.deleteAll();
    }

    @Test
    public void testAssignSkieurToPiste(Long numSkieur, Long numPiste) {
        assertNotNull(numSkieur);
        assertNotNull(numPiste);
        Piste p = new Piste();
        p.setNumPiste(numPiste);
        LocalDate date = LocalDate.of(1999, 2, 13);
        Skieur SK1 = Skieur.builder()
                .numSkieur(numSkieur)
                .nomS("Kass")
                .prenomS("Aziz")
                .dateNaissance(date)
                .ville("Manouba")
                .build();
        Skieur SavedSkieur = SS.assignSkieurToPiste(numSkieur,numPiste);
        assertNotNull(SavedSkieur);
        assertEquals(SavedSkieur,SK1);
        when(SSM.assignSkieurToPiste(numSkieur,numPiste)).thenReturn(SK1);

    }
    @Test
    public void testAddSkieurAndAssignToCourse(Skieur skieur, Long numCourse) {
        assertNotNull(numCourse);
        assertNotNull(skieur);
        Skieur savedS = SS.addSkieurAndAssignToCourse(skieur,numCourse);
        assertNotNull(savedS);
        assertEquals(savedS,skieur);
        when(SSM.addSkieurAndAssignToCourse(skieur,numCourse)).thenReturn(skieur);
    }

    @Test
    public void testRetrieveSkieursByTypeAbonnement(TypeAbonnement typeAbonnement) {
        assertNotNull(typeAbonnement);
        Abonnement AB = new Abonnement();
        AB.setTypeAbon(typeAbonnement);
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
        List<Skieur> LSsaved = SS.retrieveSkieursByTypeAbonnement(typeAbonnement);
        assertNotNull(LSsaved);
        assertEquals(LSsaved.get(0),SK1);
        assertEquals(LSsaved.get(1),SK2);

        when(SSM.retrieveSkieursByTypeAbonnement(typeAbonnement)).thenReturn(Arrays.asList(SK1,SK2));


    }
    @Test
    public void testNombreSkieursParCouleurPiste() {


        Piste p1 = Piste.builder().nomPiste("RP").numPiste(123L).couleur(Couleur.ROUGE).build();
        Piste p2 = Piste.builder().nomPiste("RPNew").numPiste(124L).couleur(Couleur.ROUGE).build();
        Piste p3 = Piste.builder().nomPiste("RPLarge").numPiste(125L).couleur(Couleur.ROUGE).build();

        Piste p4 = Piste.builder().nomPiste("BB").numPiste(128L).couleur(Couleur.BLEU).build();
        Piste p5 = Piste.builder().nomPiste("BBL").numPiste(129L).couleur(Couleur.BLEU).build();

        Piste p6 = Piste.builder().nomPiste("Noir").numPiste(155L).couleur(Couleur.NOIR).build();

        Set<Piste> ListPistes = null ;
        ListPistes.add(p1) ;
        ListPistes.add(p2) ;
        ListPistes.add(p3) ;
        ListPistes.add(p4) ;
        ListPistes.add(p5) ;
        ListPistes.add(p6) ;

        EnumMap<Couleur, Integer> CM = null;
        CM.put(Couleur.ROUGE,3);
        CM.put(Couleur.BLEU,2);
        CM.put(Couleur.NOIR,1);
        CM.put(Couleur.VERT,0);
        LocalDate date = LocalDate.of(1999, 2, 13);
        Skieur SK1 = Skieur.builder()
                .numSkieur(14354580L)
                .nomS("Kass")
                .prenomS("Aziz")
                .dateNaissance(date)
                .ville("Manouba")
                .build();
        SK1.setPistes(ListPistes);

        EnumMap<Couleur, Integer> Result = SS.nombreSkieursParCouleurPiste();
        assertNotNull(Result);
        assertEquals(Result,CM);
    }

     @Test
     public void testAddSkieurAndAssignToCourse2(Skieur skieur, Long numCourse) {
         Cours C = new Cours();
         C.setNumCours(numCourse);
         Skieur savedS = SS.addSkieurAndAssignToCourse(skieur,numCourse);
         assertNotNull(savedS);
         assertEquals(savedS,skieur);
         List<Cours> ListC = null;
         Set<Inscription> inscriptions;
         inscriptions= skieur.getInscriptions();
         inscriptions.stream().forEach(
                 inscription -> ListC.add(inscription.getCours())
         );
         assertEquals(1,ListC.contains(C))  ;

     }



}
