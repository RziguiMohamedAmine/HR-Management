package hr.server.serverhr.services;

import hr.server.serverhr.entities.Materials;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.MaterialsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MaterialsService implements IMaterialsService{

    @Autowired
    MaterialsRepository materialsRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Materials createMaterial(Materials materialResource) {
        if (materialResource.getEmployee() != null) {
            employeeRepository.findById(materialResource.getEmployee().getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
        }
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

        if (materialResource.getEmployee() != null) {
            employeeRepository.findById(materialResource.getEmployee().getIdEmployee())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));
            existingMaterial.setEmployee(materialResource.getEmployee());
        }

        return materialsRepository.save(existingMaterial);
    }

    @Override
    public void deleteMaterial(Long id) {
        materialsRepository.deleteById(id);
    }

    @Override
    public List<Materials> getMaterialsByEmployeeId(int employeeId) {
        return materialsRepository.findByEmployeeIdEmployee(employeeId);
    }
}
