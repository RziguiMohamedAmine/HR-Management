package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.services.ISalaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/salaries")
public class SalaryController {

    @Autowired
    ISalaryService salaryService;

    @PostMapping("/addupdateSalary")
    public Salary createOrUpdateSalary(@RequestBody Salary salary) {
        return salaryService.createOrUpdateSalary(salary);
    }

    // Get salary by employee ID
    @GetMapping("/employee/{employeeId}")
    public Salary getSalaryByEmployeeId(@PathVariable int employeeId) {
        return salaryService.getSalaryByEmployeeId(employeeId);
    }

}
