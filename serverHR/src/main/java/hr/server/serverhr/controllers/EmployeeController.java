package hr.server.serverhr.controllers;


import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.services.EmployeeService;
import hr.server.serverhr.services.IEmployeeService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    IEmployeeService employeeService;

    @PostMapping("/ajouterEmployee")
    Employee ajouterEmployee(@RequestBody Employee employee)
    {
        return employeeService.AjouterEmployee(employee);
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


}
