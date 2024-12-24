package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Contract;
import hr.server.serverhr.services.IContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HrMangement/Contracts")
public class ContractController {
    @Autowired
    private IContractService contractService;

    @PostMapping("/add/{employeeId}")
    public ResponseEntity<String> addContract(@PathVariable int employeeId, @RequestBody Contract contract) {
        try {
            contractService.addContract(employeeId, contract);
            return ResponseEntity.ok("Contract added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/active")
    public ResponseEntity<List<Contract>> getActiveContracts() {
        List<Contract> activeContracts = contractService.getActiveContracts();
        return ResponseEntity.ok(activeContracts);
    }

    @DeleteMapping("/delete-expired")
    public ResponseEntity<String> deleteExpiredContracts() {
        try {
            contractService.deleteExpiredContracts();
            return ResponseEntity.ok("Expired contracts deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
