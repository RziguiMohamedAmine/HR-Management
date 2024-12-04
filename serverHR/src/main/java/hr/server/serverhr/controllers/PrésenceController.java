package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.services.IPrésenceService;
import hr.server.serverhr.services.PrésenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

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


    @PostMapping("/{presenceId}/arrival")
    public ResponseEntity<Présence> addArrival(
            @PathVariable int presenceId,
            @RequestBody LocalDateTime arrivalTime) {
        Présence updatedPrésence = présenceService.addArrival(presenceId, arrivalTime);
        return ResponseEntity.ok(updatedPrésence);
    }

    @PostMapping("/{presenceId}/departure")
    public ResponseEntity<Présence> addDeparture(
            @PathVariable int presenceId,
            @RequestBody LocalDateTime departureTime) {
        Présence updatedPrésence = présenceService.addDeparture(presenceId, departureTime);
        return ResponseEntity.ok(updatedPrésence);
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

    @PostMapping("/calculate-bonus/{employeeId}")
    public void calculateAndAddBonusToSalary(@PathVariable int employeeId) {
        présenceService.updateSalaryWithExtraHoursBonus(employeeId);
    }

    @PutMapping("/{id}/justify")
    public ResponseEntity<Présence> justifyAbsence(@PathVariable int id, @RequestBody Map<String, String> request) {
        String justification = request.get("justification");
        if (justification == null || justification.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Présence updatedPrésence = présenceService.justifyAbsence(id, justification);
        return ResponseEntity.ok(updatedPrésence);
    }

}
