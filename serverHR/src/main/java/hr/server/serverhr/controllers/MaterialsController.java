package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Allocation;
import hr.server.serverhr.entities.Materials;
import hr.server.serverhr.services.IMaterialsService;
import hr.server.serverhr.services.MaterialsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Materials")
@Tag(name = "Materials Management")
public class MaterialsController {

    @Autowired
    IMaterialsService materialsService;

    @PostMapping
    public Materials createMaterial(@RequestBody Materials materialResource) {
        return materialsService.createMaterial(materialResource);
    }

    @GetMapping("/{id}")
    public Materials getMaterialById(@PathVariable Long id) {
        return materialsService.getMaterialById(id);
    }

    @GetMapping
    public List<Materials> getAllMaterials() {
        return materialsService.getAllMaterials();
    }

    @PutMapping("/{id}")
    public Materials updateMaterial(@PathVariable Long id, @RequestBody Materials materialResource) {
        return materialsService.updateMaterial(id, materialResource);
    }

    @DeleteMapping("/{id}")
    public void deleteMaterial(@PathVariable Long id) {
        materialsService.deleteMaterial(id);
    }


    @PostMapping("/{matId}/allocate")
    public ResponseEntity<Allocation> allocateMaterial(
            @PathVariable Long matId,
            @RequestParam int employeeId,
            @RequestParam int quantity) {

        try {
            Allocation allocation = materialsService.allocateMaterialsToEmployee(matId, employeeId, quantity);
            return ResponseEntity.ok(allocation);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(null); // Return 400 if not enough material
        }
    }

}
