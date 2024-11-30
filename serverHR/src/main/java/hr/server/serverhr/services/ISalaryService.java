package hr.server.serverhr.services;

import hr.server.serverhr.entities.Salary;

public interface ISalaryService {

    Salary createOrUpdateSalary(Salary salary);
    Salary getSalaryByEmployeeId(int employeeId);
}
