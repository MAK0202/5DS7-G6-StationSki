package com.example.stationski.controllers;


import com.example.stationski.dto.InscriptionDto;

import com.example.stationski.entities.Inscription;
import com.example.stationski.entities.Support;
import com.example.stationski.services.IInscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
 @AllArgsConstructor
@RequestMapping("/inscription")

public class InscriptionController {
    IInscriptionService inscriptionService;


    // http://localhost:8089/stationSki/inscription/addInscriptionAndAssignToSkieurAndCourse/4/27
    @PostMapping("/addInscriptionAndAssignToSkieurAndCourse/{numSkieur}/{numCours}")
    public Inscription addSkieurAndAssignToCourse(@RequestBody Inscription inscription,
                                                  @PathVariable("numSkieur") Long numSkieur,
                                                  @PathVariable("numCours") Long numCours) {
        // Convert the DTO to the entity if necessary and call the service method
        return inscriptionService.addInscriptionAndAssignToSkieurAndCourse(inscription, numSkieur, numCours);
    }
    // http://localhost:8089/stationSki/inscription/assignInscriptionToCours/4/27
    @PutMapping("/assignInscriptionToCours/{numInscription}/{numCours}")
    public Inscription assignInscriptionToCours(@PathVariable("numInscription") Long numInscription,@PathVariable("numCours") Long numCours)
    {
        return  inscriptionService.assignInscriptionToCours(numInscription,numCours);
    }

    @GetMapping("/numWeeksCoursOfMoniteurBySupport/{numMoniteur}/{support}")
    public List<Integer> numWeeksCoursOfMoniteurBySupport(@PathVariable("numMoniteur")Long numMoniteur,
                                                             @PathVariable("support") Support support) {
        return inscriptionService.numWeeksCoursOfMoniteurBySupport(numMoniteur,support);
    }
}
