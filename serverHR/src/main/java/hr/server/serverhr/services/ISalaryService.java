package hr.server.serverhr.services;

import hr.server.serverhr.entities.PaymentHistory;
import hr.server.serverhr.entities.Salary;

import java.util.List;

public interface ISalaryService {

    Salary createOrUpdateSalary(Salary salary);
    Salary getSalaryByEmployeeId(int employeeId);
    Salary validateSalaryPayment(int employeeId);
    void validateAllSalaries();
    List<PaymentHistory> getPaymentHistoryForEmployee(int employeeId);
}
