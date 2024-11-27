package hr.server.serverhr.services;

import hr.server.serverhr.entities.Conge;
import hr.server.serverhr.entities.Employee;
import hr.server.serverhr.repositories.CongéRepository;
import hr.server.serverhr.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CongeService implements ICongeService{

    @Autowired
    CongéRepository congéRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Conge DemanderConge(Conge conge) {
        conge.setValidé(false);
        return congéRepository.save(conge);
    }

    @Override
    public Conge UpdateConge(Conge conge) {
        Conge conge1 = congéRepository.findById(conge.getId()).orElseThrow(() -> new RuntimeException("Congé not found"));
        Employee employee = employeeRepository.findById(conge1.getEmployee().getIdEmployee()).orElseThrow(() -> new RuntimeException("Employee not found"));
        conge.setEmployee(employee);
        return congéRepository.save(conge);
    }

    @Override
    public List<Conge> RetrieveHistoriqueConge(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return congéRepository.findAllByEmployee(employee);
    }

    @Override
    public void DeleteConge(int id) {
        congéRepository.deleteById(id);
    }
}
