package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;

import java.util.List;

public interface IEmployeeService {
    Employee AjouterEmployee(Employee employee);
    Employee UpdateEmployee(Employee employee);
    List<Employee> RetrieveAllEmployees();
    Employee RetrieveEmployee(int id);
    void DeleteEmployee(int id);

}
