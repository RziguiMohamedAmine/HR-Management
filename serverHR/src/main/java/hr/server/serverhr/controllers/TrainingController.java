package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Task;
import hr.server.serverhr.entities.Training;
import hr.server.serverhr.services.ITrainingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/HrMangement/training")
@Tag(name = "Training Management")
public class TrainingController {

    @Autowired
    private ITrainingService trainingService;

    @PostMapping
    public ResponseEntity<Training> addTrainingClass(@RequestBody Training trainingClass) {
        return ResponseEntity.ok(trainingService.addTrainingClass(trainingClass));
    }

    @PostMapping("/{id}/assign")
    public ResponseEntity<Training> assignEmployeesToTraining(
            @PathVariable Long id,
            @RequestBody List<Integer> employeeIds) {
        return ResponseEntity.ok(trainingService.assignEmployeesToTraining(id, employeeIds));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Training> getTrainingClassById(@PathVariable Long id) {
        return ResponseEntity.ok(trainingService.getTrainingClassById(id));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Training> getTrainingClassByName(@PathVariable String name) {
        return ResponseEntity.ok(trainingService.getTrainingClassByName(name));
    }

    @GetMapping("/GetAllTrainings")
    public List<Training> getAllTrainings(){
        return trainingService.getAllTrainings();
    }

    @GetMapping("/{trainingClassId}/employees")
    public ResponseEntity<List<Employee>> getEmployeesForTrainingClass(@PathVariable Long trainingClassId) {
        List<Employee> employees = trainingService.getEmployeesForTrainingClass(trainingClassId);
        return ResponseEntity.ok(employees);
    }
}
