package com.example.stationski.controllers;

import com.example.stationski.entities.Moniteur;
import com.example.stationski.services.IMoniteurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/moniteur")
@Tag(name = "Moniteur Management")
public class MoniteurRestController {

    IMoniteurService moniteurService;
    // http://localhost:8089/stationSki/moniteur/retrieve-all-moniteurs
    @Operation(description = "liste des moniteurs")
    @GetMapping("/retrieve-all-moniteurs")
    public List<Moniteur> getAbonnements() {
        return moniteurService.retrieveAllMoniteurs();
    }

    // http://localhost:8089/stationSki/moniteur/retrieve-moniteur/8
    @Operation(description = "récupérer un moniteur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the monitor",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Moniteur.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Moniteur not found",
                    content = @Content) })
    @GetMapping("/retrieve-moniteur/{moniteur-id}")
    public Moniteur retrieveMoniteur(@Parameter(description = "id of monitor to be searched")
                                          @PathVariable("moniteur-id") Integer moniteurId) {
            Optional<Moniteur> moniteurOptional = moniteurService.retrieveMoniteur(moniteurId);
            if(moniteurOptional.isPresent()){
                return moniteurOptional.get();
            }
            else throw new EntityNotFoundException("Moniteur not found");
}



    // http://localhost:8089/stationSki/moniteur/add-moniteur
    @Operation(description = "ajouter un moniteur")
    @PostMapping("/add-moniteur")
    public Moniteur addMoniteur(@RequestBody Moniteur m) {
        return moniteurService.addMoniteur(m);
    }
    @Operation(description = "supprimer un moniteur")
    // http://localhost:8089/stationSki/moniteur/remove-moniteur/1
    @DeleteMapping("/remove-moniteur/{moniteur-id}")
    public void removeMoniteur(@PathVariable("moniteur-id") Integer moniteurId) {
        moniteurService.deleteMoniteur(moniteurId);
    }

    @Operation(description = "modifier un moniteur")
    // http://localhost:8089/stationSki/moniteur/update-moniteur
    @PutMapping("/update-moniteur")
    public Optional<Moniteur> updateMoniteur(@PathVariable("id") Integer id, @RequestBody Moniteur m) {
        Optional<Moniteur> existingMoniteurOptional = moniteurService.retrieveMoniteur(id);
        if(existingMoniteurOptional.isPresent()){
            var existingMoniteur = existingMoniteurOptional.get();
            existingMoniteur.setNumMoniteur(m.getNumMoniteur());
            existingMoniteur.setPrime(m.getPrime());
            existingMoniteur.setNomM(m.getNomM());
            existingMoniteur.setPrenomM(m.getPrenomM());
            existingMoniteur.setDateRecru(m.getDateRecru());
            return moniteurService.updateMoniteur(existingMoniteur, id);
        }
        else throw new EntityNotFoundException("Moniteur not found");
    }

    @Operation(description = "ajouter un moniteur et affecter à un cours")
    // http://localhost:8089/stationSki/moniteur/addMoniteurAndAssignToCourse
    @PostMapping("/addMoniteurAndAssignToCourse")
    public Moniteur addMoniteurAndAssignToCourse(@RequestBody Moniteur m) {
        return moniteurService.addMoniteurAndAssignToCourse(m);
    }

    // http://localhost:8089/stationSki/moniteur/bestMoniteur
    @Operation(description = "best moniteurs")
    @GetMapping("/bestMoniteur")
    public Moniteur bestMoniteur() {
        return moniteurService.bestMoniteur();
    }
}
