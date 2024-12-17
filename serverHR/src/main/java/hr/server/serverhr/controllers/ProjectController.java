package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Projet;
import hr.server.serverhr.services.IEmployeeService;
import hr.server.serverhr.services.IProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/HrManagement/Project")
public class ProjectController {

    @Autowired
    IProjectService projectService;

    @PostMapping("/ajouterProjet")
    Projet ajouterProjet(@RequestBody Projet projet){
        return projectService.AjouterProjet(projet);
    }

    @PutMapping("/updateProjet")
    Projet updateProjet(@RequestBody Projet projet){
        return projectService.UpdateProjet(projet);
    }

    @GetMapping("/afficherAllProjects")
    List<Projet> getAllProjet(@PathVariable int id){
        return projectService.RetrieveAllProject();
    }

    @GetMapping("/afficherProjet/{id}")
    Projet retreiveProjet(@PathVariable int id){
        return projectService.RetrieveProjet(id);
    }

    @DeleteMapping("/deleteProjet")
    void deleteProjet(@PathVariable int id){
        projectService.DeleteProjet(id);
    }

    @GetMapping("/{projectId}/employees")
    public ResponseEntity<Set<Employee>> getEmployeesByProject(@PathVariable int projectId) {
        Set<Employee> employees = projectService.getEmployeesByProject(projectId);
        return ResponseEntity.ok(employees);
    }

}
