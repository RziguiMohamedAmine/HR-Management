package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.services.IPrésenceService;
import hr.server.serverhr.services.PrésenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Présence")
@Tag(name = "Présence Management")
public class PrésenceController {

    IPrésenceService présenceService;

    @PostMapping("/ajouterPrésence")
    Présence ajouterPrésence(@RequestBody Présence présence){
        return présenceService.ajouterPrésence(présence);
    }

    @PutMapping("/updatePrésence")
    Présence updatePrésence(Présence présence)
    {
        return présenceService.updatePrésence(présence);
    }

    @DeleteMapping("/deletePrésence/{id}")
    void deletePrésence(@PathVariable int id){
        présenceService.DeletePresence(id);
    }

    @GetMapping("/afficherAllPrésence/{id}")
    List<Présence> retrieveAllPrésence(@PathVariable int id){
        return présenceService.retrieveAllPrésence(id);
    }

    @GetMapping("/getAllDayPresence/{date}")
     List<Présence> getAllDayPresence(@PathVariable String date){
        return présenceService.getAllDayPresence(date);
    }


}
