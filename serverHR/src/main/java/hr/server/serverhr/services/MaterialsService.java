package hr.server.serverhr.services;

import hr.server.serverhr.entities.Allocation;
import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Materials;
import hr.server.serverhr.repositories.AllocationRepository;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.MaterialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class MaterialsService implements IMaterialsService{

    @Autowired
    MaterialsRepository materialsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AllocationRepository allocationRepository;

    @Override
    public Materials createMaterial(Materials materialResource) {
        return materialsRepository.save(materialResource);
    }

    @Override
    public Materials getMaterialById(Long id) {
        return materialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));
    }

    @Override
    public List<Materials> getAllMaterials() {
        return materialsRepository.findAll();
    }

    @Override
    public Materials updateMaterial(Long id, Materials materialResource) {

        Materials existingMaterial = materialsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        existingMaterial.setName(materialResource.getName());
        existingMaterial.setQuantity(materialResource.getQuantity());
        existingMaterial.setStatus(materialResource.getStatus());
        existingMaterial.setCost(materialResource.getCost());


        return materialsRepository.save(existingMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        materialsRepository.deleteById(id);
    }


    @Override
    public Allocation allocateMaterialsToEmployee(Long matId, int employeeId, int quantity) {
        Materials material = materialsRepository.findById(matId)
                .orElseThrow(() -> new RuntimeException("Material not found"));

        // Check if enough quantity is available
        material.decreaseQuantity(quantity);
        materialsRepository.save(material); // Save the updated material quantity

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));

        // Create a new allocation entry
        Allocation allocation = new Allocation();
        allocation.setMaterial(material);
        allocation.setEmployee(employee); // Set the employee (you would have a valid Employee object here)
        allocation.setQuantityAllocated(quantity);
        allocation.setAllocationDate(LocalDateTime.now());

        // Save the allocation to the database
        return allocationRepository.save(allocation);
    }


}
