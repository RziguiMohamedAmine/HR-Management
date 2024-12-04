package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Greeting;
import hr.server.serverhr.entities.Notification;
import hr.server.serverhr.entities.Training;
import hr.server.serverhr.services.IEmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/HrMangement/Employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @PostMapping("/ajouterEmployee")
    Employee ajouterEmployee(@RequestBody Employee employee)
    {
        Employee addedEmployee = employeeService.AjouterEmployee(employee);

        // Send a notification message after adding a new employee
        String message = "Employee added: " + addedEmployee.getNom();
        messagingTemplate.convertAndSend("/topic/greetings", new Notification(message)); // Send notification to WebSocket

        return addedEmployee;
    }

    @PutMapping("/updateEmployee")
    Employee updateEmployee(@RequestBody Employee employee)
    {
        return employeeService.UpdateEmployee(employee);
    }

    @GetMapping("/afficherAllEmployee")
    List<Employee> getAllEmployee(){
        return employeeService.RetrieveAllEmployees();
    }

    @GetMapping("/afficherEmployee/{id}")
    Employee retrieveEmployee(@PathVariable int id){
        return employeeService.RetrieveEmployee(id);
    }

    @DeleteMapping("/deleteEmployee")
    void deleteEmployee(@PathVariable int id){
        employeeService.DeleteEmployee(id);
    }

    @GetMapping("/{employeeId}/training-classes")
    public ResponseEntity<List<Training>> getEmployeeTrainingClasses(@PathVariable int employeeId) {
        List<Training> trainingClasses = employeeService.getEmployeeTrainings(employeeId);
        return ResponseEntity.ok(trainingClasses);
    }


}
