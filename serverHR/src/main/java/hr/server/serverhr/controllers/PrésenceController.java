package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Présence;
import hr.server.serverhr.repositories.PrésenceRepository;
import hr.server.serverhr.services.IPrésenceService;
import hr.server.serverhr.services.PrésenceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @Autowired
    PrésenceRepository présenceRepository;

    @PostMapping("/ajouterPrésence")
    Présence ajouterPrésence(@RequestBody Présence présence){
        return présenceService.ajouterPrésence(présence);
    }


    @PostMapping("/arrival/{employeeId}")
    public ResponseEntity<Présence> addArrival(
            @PathVariable int employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime arrivalTime) {
        Présence updatedPrésence = présenceService.addArrival(employeeId, date, arrivalTime);
        return ResponseEntity.ok(updatedPrésence);
    }

    @PostMapping("/departure/{employeeId}")
    public ResponseEntity<Présence> addDeparture(
            @PathVariable int employeeId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureTime) {
        Présence updatedPrésence = présenceService.addDeparture(employeeId, date, departureTime);
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

    @GetMapping("/afficherAllPrésence")
    List<Présence> getAllPrésence(){
        return présenceService.getAllPrésence();
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

    @GetMapping
    public ResponseEntity<List<Présence>> getPresences(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month
    ) {
        if (year != null && month != null) {
            return ResponseEntity.ok(présenceRepository.findByYearAndMonth(year, month));
        }
        return ResponseEntity.ok(présenceRepository.findAll());
    }



}
