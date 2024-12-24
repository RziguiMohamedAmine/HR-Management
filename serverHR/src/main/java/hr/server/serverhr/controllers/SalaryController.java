package hr.server.serverhr.controllers;

import hr.server.serverhr.entities.PaymentHistory;
import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.services.ISalaryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/HrMangement/salaries")
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

    @PostMapping("/validate/{employeeId}")
    public Salary validateSalary(@PathVariable int employeeId) {
        return salaryService.validateSalaryPayment(employeeId);
    }

    // Validate salary payments for all employees
    @PostMapping("/validate-all")
    public void validateAllSalaries() {
        salaryService.validateAllSalaries();
    }

    // Get payment history for an employee
    @GetMapping("/history/{employeeId}")
    public List<PaymentHistory> getPaymentHistory(@PathVariable int employeeId) {
        return salaryService.getPaymentHistoryForEmployee(employeeId);
    }

}
