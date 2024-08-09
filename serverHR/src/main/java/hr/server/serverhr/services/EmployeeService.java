package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;



    @Override
    public Employee AjouterEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee UpdateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> RetrieveAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee RetrieveEmployee(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public void DeleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
