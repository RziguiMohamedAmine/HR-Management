package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.PaymentHistory;
import hr.server.serverhr.entities.Salary;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.PaymentHistoryRepository;
import hr.server.serverhr.repositories.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class SalaryService implements ISalaryService{

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;
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

    @Override
    public Salary validateSalaryPayment(int employeeId) {
        // Fetch the salary record for the employee
        Salary salary = salaryRepository.findByEmployeeIdEmployee(employeeId)
                .orElseThrow(() -> new RuntimeException("Salary record not found for employee"));

        // Check if payment for the current month is already validated
        if (salary.isPaymentValidated() && salary.getLastPaymentDate() != null &&
                salary.getLastPaymentDate().getMonth() == LocalDate.now().getMonth()) {
            throw new RuntimeException("Salary for this month has already been validated.");
        }

        PaymentHistory paymentHistory = PaymentHistory.builder()
                .employee(salary.getEmployee())
                .baseSalary(salary.getBaseSalary())
                .bonuses(salary.getBonuses())
                .deductions(salary.getDeductions())
                .totalPaid(salary.getBaseSalary() + salary.getBonuses() - salary.getDeductions())
                .paymentDate(LocalDate.now())
                .build();
        paymentHistoryRepository.save(paymentHistory);

        // Process payment validation
        salary.setPaymentValidated(true);
        salary.setLastPaymentDate(LocalDate.now());

        // Reset bonuses and deductions to 0
        salary.setBonuses(0.0);
        salary.setDeductions(0.0);

        // Save the updated salary record
        return salaryRepository.save(salary);
    }

    @Override
    public void validateAllSalaries() {
        List<Salary> allSalaries = salaryRepository.findAll();
        allSalaries.forEach(salary -> {
            if (!salary.isPaymentValidated() ||
                    salary.getLastPaymentDate() == null ||
                    salary.getLastPaymentDate().getMonth() != LocalDate.now().getMonth()) {

                // Validate and reset
                salary.setPaymentValidated(true);
                salary.setLastPaymentDate(LocalDate.now());
                salary.setBonuses(0.0);
                salary.setDeductions(0.0);

                salaryRepository.save(salary);
            }
        });
    }

    @Override
    public List<PaymentHistory> getPaymentHistoryForEmployee(int employeeId) {
        return paymentHistoryRepository.findByEmployeeIdEmployee(employeeId);
    }





}
