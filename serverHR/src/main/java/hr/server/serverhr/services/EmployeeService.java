package hr.server.serverhr.services;

import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.entities.Projet;
import hr.server.serverhr.entities.Training;
import hr.server.serverhr.repositories.EmployeeRepository;
import hr.server.serverhr.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService{
    @Autowired
    private ProjectRepository projectRepository;

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
    public Employee AssignProject(int employeeId, Projet projet){
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new RuntimeException("Employee not found"));
        employee.setProjet(projet);
       return employeeRepository.save(employee);
    }

    @Override
    public List<Projet> getEmployeeProjects(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + employeeId));

        List<Projet> projects = new ArrayList<>();

        // Add the project the employee is part of
        if (employee.getProjet() != null) {
            projects.add(employee.getProjet());
        }

        // Add the project managed by the employee (if any)

        return projects;
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

    @Override
    public List<Training> getEmployeeTrainings(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()
                -> new RuntimeException("Employee not found"));
        List<Training> employeeTrainingClasses = employee.getTrainingClasses();
        return employeeTrainingClasses;
    }

    @Scheduled(cron = "0 0 0 1 * ?") // At midnight, on the first day of each month
    public void incrementSoldeConge() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            employee.setSoldecongé(employee.getSoldecongé() + 2);
        }
        employeeRepository.saveAll(employees); // Save the updated employees
        System.out.println("soldecongé updated for all employees!");
    }


}
