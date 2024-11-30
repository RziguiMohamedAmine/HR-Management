package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SalaryService implements ISalaryService{

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Salary createOrUpdateSalary(Salary salary) {
        Employee employee = employeeRepository.findById(salary.getEmployee().getIdEmployee())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Salary existingSalary = salaryRepository.findByEmployeeIdEmployee(employee.getIdEmployee()).orElse(null);
        if (existingSalary != null) {
            // Update the existing salary
            existingSalary.setBaseSalary(salary.getBaseSalary());
            existingSalary.setBonuses(salary.getBonuses());
            existingSalary.setDeductions(salary.getDeductions());
            existingSalary.setPaymentFrequency(salary.getPaymentFrequency());
            return salaryRepository.save(existingSalary);
        } else {
            // Create a new salary
            salary.setEmployee(employee);
            return salaryRepository.save(salary);
        }

    }

    @Override
    public Salary getSalaryByEmployeeId(int employeeId) {
        return salaryRepository.findByEmployeeIdEmployee(employeeId)
                .orElseThrow(() -> new RuntimeException("Salary not found for the employee"));
    }

}
