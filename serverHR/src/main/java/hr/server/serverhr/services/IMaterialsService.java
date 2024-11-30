package hr.server.serverhr.services;

import hr.server.serverhr.entities.Materials;

import java.util.List;

public interface IMaterialsService {
    Materials createMaterial(Materials materialResource);
    Materials getMaterialById(Long id);
    List<Materials> getAllMaterials();
    Materials updateMaterial(Long id, Materials materialResource);
    void deleteMaterial(Long id);
    List<Materials> getMaterialsByEmployeeId(int employeeId);
}
