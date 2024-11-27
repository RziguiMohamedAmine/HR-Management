package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Conge;
import hr.server.serverhr.services.CongeService;
import hr.server.serverhr.services.ICongeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Conge")
@Tag(name = "Conge Management")
public class CongeController {

    ICongeService congeService;
    @PostMapping("/demanderCongé")
    Conge DemanderConge(@RequestBody Conge conge){
        return congeService.DemanderConge(conge);
    }

    @PutMapping("/modifierCongé")
    Conge modifierConger(@RequestBody Conge conge){
        return congeService.UpdateConge(conge);
    }

    @GetMapping("/getCongé/{id}")
    List<Conge> retrieveHistoriqueCongé(@PathVariable int id){
       return congeService.RetrieveHistoriqueConge(id);
    }

    @DeleteMapping("/deleteCongé/{id}")
    void DeleteConge(@PathVariable int id){
        congeService.DeleteConge(id);
    }

}
