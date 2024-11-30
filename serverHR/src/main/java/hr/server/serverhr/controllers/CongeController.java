package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Conge;
import hr.server.serverhr.services.CongeService;
import hr.server.serverhr.services.ICongeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Conge")
@Tag(name = "Conge Management")
public class CongeController {

    @Autowired
    ICongeService congeService;
    @PostMapping("/demanderCongé")
    Conge DemanderConge(@RequestBody Conge conge){
        return congeService.DemanderConge(conge);
    }

    @PutMapping("/approuverConge")
    boolean approuverConge(@RequestBody Conge conge){
        return congeService.approuverConge(conge);
    }

    @PutMapping("/refuserConge")
    boolean refuserConge(@RequestBody Conge conge){
        return congeService.refuserConge(conge);
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
